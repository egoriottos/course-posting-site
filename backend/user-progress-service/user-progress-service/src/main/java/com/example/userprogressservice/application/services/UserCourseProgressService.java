package com.example.userprogressservice.application.services;

import com.example.userprogressservice.application.services.request.CertificateRequest;
import com.example.userprogressservice.commands.userCourseProgress.CreateUserCourseProgressCommand;
import com.example.userprogressservice.commands.userCourseProgress.UpdateUserCourseProgressCommand;
import com.example.userprogressservice.domain.entity.UserCourseProgress;
import com.example.userprogressservice.dto.UserCourseProgressDto;
import com.example.userprogressservice.repository.UserCourseProgressRepository;
import com.example.userprogressservice.repository.customRepository.CustomCourseProgressRepository;
import com.example.userprogressservice.searchParams.SearchCourseProgressParam;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCourseProgressService {
    private final UserCourseProgressRepository userCourseProgressRepository;
    private final CustomCourseProgressRepository customCourseProgressRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    @Value("${lesson.service.url}")
    private String lessonServiceUrl;

    @Value("${test.service.url}")
    private String testServiceUrl;

    @Value("${certificate.service.url}")
    private String certificateServiceUrl;
    //создать прогресс курса пользователя
    @Transactional
    public void createUserCourseProgress(CreateUserCourseProgressCommand command){
        var courseProgress = UserCourseProgress.builder()
                .userId(command.getUserId())
                .courseId(command.getCourseId())
                .progressPercentage(0.0)
                .completed(false)
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        userCourseProgressRepository.save(courseProgress);
    }
    //удалить прогресс курса
    @Transactional
    public void deleteCourseProgress(Long courseProgressId){
        userCourseProgressRepository.deleteById(courseProgressId);
    }
    //обновить прогресс курса пользователя
    @Transactional
    public void updateUserCourseProgress(Long id,UpdateUserCourseProgressCommand command){
        var userCourseProgress = userCourseProgressRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Course not found"));
        if(!userCourseProgress.getCourseId().equals(command.getCourseId())
                || !userCourseProgress.getProgressPercentage().equals(command.getProgressPercentage())
                || !userCourseProgress.getCompleted().equals(command.getCompleted()))
        {
            userCourseProgress.setCourseId(command.getCourseId());
            userCourseProgress.setProgressPercentage(command.getProgressPercentage());
            userCourseProgress.setCompleted(command.getCompleted());
            userCourseProgress.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            userCourseProgressRepository.save(userCourseProgress);
        }
    }
    //получить список прогрессов пользователя по айди пользователя
    public List<UserCourseProgressDto> getUserCoursesProgress(Long userId){
        return userCourseProgressRepository.findByUserId(userId).stream()
                .map(obj->modelMapper.map(obj,UserCourseProgressDto.class)).collect(Collectors.toList());

    }
    //получить прогресс по конкретному курсу пользователя
    public UserCourseProgressDto getUserCourseProgress(Long userId, Long courseId){
        return modelMapper.map(userCourseProgressRepository.findByUserIdAndCourseId(userId,courseId),UserCourseProgressDto.class);
    }
    //получить прогресс по курсу пользователя из таблицы userCourseProgress
    public UserCourseProgressDto getUserCourseProgress(Long id){
        return modelMapper.map(userCourseProgressRepository.findById(id),UserCourseProgressDto.class);
    }
    //увеличить прогресс выполнения
    @Transactional
    public UserCourseProgress incrementProgress(Long userId, Long courseId, Long lessonId, Long testId) {
        UserCourseProgress courseProgress = userCourseProgressRepository.findByUserIdAndCourseId(userId, courseId);

        // Получаем количество уроков из микросервиса уроков
        Integer totalLessons = getLessonCountByCourseId(courseId);
        if (totalLessons == null || totalLessons <= 0) {
            throw new EntityNotFoundException("No lessons found for the course");
        }

        boolean progressUpdated = false;

        // Проверяем, что урок еще не был завершен
        if (lessonId != null && !courseProgress.getCompletedLessons().contains(lessonId)) {
            courseProgress.getCompletedLessons().add(lessonId);
            progressUpdated = true;
        }

        // Проверяем, что тест еще не был завершен
        if (testId != null && !courseProgress.getCompletedTests().contains(testId)) {
            courseProgress.getCompletedTests().add(testId);
            progressUpdated = true;
        }

        if (progressUpdated) {
            // Пересчитываем прогресс
            double newProgress = calculateCourseProgress(courseProgress, totalLessons);
            courseProgress.setProgressPercentage(newProgress);

            if (newProgress >= 100.0) {
                courseProgress.setCompleted(true);
                generateCertificate(userId, courseId);
            }

            return userCourseProgressRepository.save(courseProgress);
        }

        return courseProgress;
    }

    private Integer getLessonCountByCourseId(Long courseId) {
        String url = lessonServiceUrl + "/api/courses/" + courseId + "/lessons/count";
        return restTemplate.getForObject(url, Integer.class);
    }

    private int getTotalTestsForCourse(Long courseId) {
        String url = testServiceUrl + "/api/courses/" + courseId + "/tests/count";
        return restTemplate.getForObject(url, Integer.class);
    }

    private double calculateCourseProgress(UserCourseProgress courseProgress, Integer totalLessons) {
        // Проверяем, что общее количество уроков больше нуля
        if (totalLessons <= 0) {
            throw new IllegalArgumentException("Total lessons must be greater than zero.");
        }

        // Получаем количество завершенных уроков
        int completedLessonsCount = courseProgress.getCompletedLessons().size();

        // Вычисляем долю за каждый завершенный урок
        double progressPerLesson = 100.0 / totalLessons;

        // Вычисляем прогресс по урокам
        double lessonProgress = completedLessonsCount * progressPerLesson;

        // Получаем количество завершенных тестов
        int totalTests = getTotalTestsForCourse(courseProgress.getCourseId());
        double testProgress = (totalTests > 0) ? (courseProgress.getCompletedTests().size() / (double) totalTests) * 100.0 : 0.0;

        // Определяем долю уроков и тестов в общем прогрессе
        double courseCompletionPercentage = 0.5; // Пример веса для уроков и тестов
        double combinedLessonProgress = (lessonProgress / 100.0) * courseCompletionPercentage * 100.0;
        double combinedTestProgress = (testProgress / 100.0) * (1 - courseCompletionPercentage) * 100.0;

        // Комбинированный прогресс
        double combinedProgress = combinedLessonProgress + combinedTestProgress;

        // Убедиться, что прогресс не превышает 100%
        return Math.min(combinedProgress, 100.0);
    }
    //генерация сертификата
    private void generateCertificate(Long userId, Long courseId) {
        String url = certificateServiceUrl + "/api/certificates";
        // Создаем тело запроса (DTO)
        CertificateRequest request = new CertificateRequest(userId, courseId);
        restTemplate.postForObject(url, request, Void.class);
    }
    public List<UserCourseProgressDto> search(SearchCourseProgressParam params){
       return customCourseProgressRepository.search(params).stream()
               .map(obj->modelMapper.map(obj,UserCourseProgressDto.class)).collect(Collectors.toList());
    }
}
