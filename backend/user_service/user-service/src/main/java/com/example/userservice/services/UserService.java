package com.example.userservice.services;

import com.example.userservice.commands.UpdateUserCommand;
import com.example.userservice.domain.entity.Image;
import com.example.userservice.domain.entity.Student;
import com.example.userservice.domain.entity.Teacher;
import com.example.userservice.domain.entity.User;
import com.example.userservice.domain.entity.enums.Roles;
import com.example.userservice.query.SearchParams;
import com.example.userservice.query.requests.RequestUserData;
import com.example.userservice.repository.CustomUserRepository;
import com.example.userservice.repository.interfaces.StudentRepository;
import com.example.userservice.repository.interfaces.TeacherRepository;
import com.example.userservice.repository.interfaces.UserRepository;
import com.example.userservice.responses.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    private final ImageService imageService;
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createUser(RequestUserData createUserCommand) {
        var user = User.builder()
                .login(createUserCommand.getLogin())
                .email(createUserCommand.getEmail())
                .firstname(createUserCommand.getFirstname())
                .lastname(createUserCommand.getLastname())
                .profileImage(createUserCommand.getProfileImage())
                .phone(createUserCommand.getPhone())
                .dateOfBirth(createUserCommand.getDateOfBirth())
                .roles(createUserCommand.getRoles())
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                        .build();
        userRepository.save(user);
        if(user.getRoles().equals(Roles.STUDENT)){
            var student = Student.builder()
                            .owner(user)
                                    .coursesId(Collections.emptyList())
                    .quizResultsId(Collections.emptyList())
                                            .build();
            studentRepository.save(student);
        }
        if(user.getRoles().equals(Roles.TEACHER)){
            var teacher = Teacher.builder()
                    .owner(user)
                    .coursesId(Collections.emptyList())
                    .rating(0f)
                    .build();
            teacherRepository.save(teacher);
        }
    }

    public List<UserResponse> search(SearchParams params){
        List<User> users =customUserRepository.search(params);
        return users.stream().map(obj->modelMapper.map(obj, UserResponse.class)).toList();
    }
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    @Transactional
    public void update(Long id,UpdateUserCommand updateUserCommand){
        var user = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found"));
        if(!user.getLogin().equals(updateUserCommand.getLogin())
                || !user.getEmail().equals(updateUserCommand.getEmail())
                || !user.getFirstname().equals(updateUserCommand.getFirstname())
                || !user.getLastname().equals(updateUserCommand.getLastname())
                || !user.getPhone().equals(updateUserCommand.getPhone())
                || !user.getDateOfBirth().equals(updateUserCommand.getDateOfBirth()))
        {
            user.setLogin(updateUserCommand.getLogin());
            user.setEmail(updateUserCommand.getEmail());
            user.setFirstname(updateUserCommand.getFirstname());
            user.setLastname(updateUserCommand.getLastname());
            user.setPhone(updateUserCommand.getPhone());
            user.setDateOfBirth(updateUserCommand.getDateOfBirth());
            user.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            userRepository.save(user);
        }

    }

    public User updateUserImage(Long userId, MultipartFile file, String directory) throws IOException {
        // Сохраняем изображение
        Image savedImage = imageService.save(directory, file);

        // Находим пользователя по ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Обновляем ссылку на изображение
        user.setProfileImage(savedImage);

        // Сохраняем изменения в БД
        return userRepository.save(user);
    }
    public User assignImageToUser(Long userId, Long imageId) {
        // Находим пользователя по ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Находим изображение по ID
        Image image =imageService.getImageById(imageId);
        // Обновляем ссылку на изображение
        user.setProfileImage(image);

        // Сохраняем изменения в БД
        return userRepository.save(user);
    }
}
