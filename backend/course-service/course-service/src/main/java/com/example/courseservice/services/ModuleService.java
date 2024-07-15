package com.example.courseservice.services;

import com.example.courseservice.application.commands.module.CreateModuleCommand;
import com.example.courseservice.application.commands.module.ModuleResponse;
import com.example.courseservice.application.commands.module.UpdateModuleCommand;
import com.example.courseservice.application.commands.module.query.ModuleQuery;
import com.example.courseservice.domain.entities.Course;
import com.example.courseservice.domain.entities.Module;
import com.example.courseservice.infrostructure.config.repositories.CourseRepository;
import com.example.courseservice.infrostructure.config.repositories.CustomModuleRepository;
import com.example.courseservice.infrostructure.config.repositories.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ModuleService {
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final CustomModuleRepository customModuleRepository;
    private final ModelMapper modelMapper;
    //Назначение модуля к конкретному курсу.

    //Получение списка всех модулей.
    //Получение информации о конкретном модуле по его идентификатору.
    //Получение списка уроков для конкретного модуля.

    //Добавление, редактирование и удаление уроков в модуле.
    //Связывание уроков с модулем.
    @Transactional
    public void createModule(Long id, CreateModuleCommand module) {
        Course course = courseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        int maxOrder = moduleRepository.findMaxOrderByCourse(course).orElse(0);
        var moduleEntity = Module.builder()
                .course(course)
                .title(module.getTitle())
                .serialNumber(maxOrder + 1)
                .description(module.getDescription())
                .lessonsId(module.getLessonsId())
                .quizId(module.getQuizId())
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();

        moduleRepository.save(moduleEntity);
    }

    @Transactional
    public void deleteModule(Long moduleId) {
        Optional<Module> moduleOptional = moduleRepository.findById(moduleId);
        if (moduleOptional.isEmpty()) {
            throw new IllegalArgumentException("Module not found");
        }

        Module module = moduleOptional.get();
        Course course = module.getCourse();
        int order = module.getSerialNumber();

        List<Module> modules = moduleRepository.findByCourseAndSerialNumberGreaterThan(course, order);
        for (Module m : modules) {
            m.setSerialNumber(m.getSerialNumber() - 1);
            moduleRepository.save(m);
        }
    }

    public List<Module> getAllModulesFromCourse(Long courseId) {
        return moduleRepository.findModulesByCourseId(courseId);

    }

    @Transactional
    public Module updateModule(Long id, UpdateModuleCommand command) {
        var moduleEntity = moduleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!moduleEntity.getDescription().equals(command.getDescription())
                || !moduleEntity.getLessonsId().equals(command.getLessonsId())
                || !moduleEntity.getSerialNumber().equals(command.getOrder())
                || !moduleEntity.getCourse().equals(command.getCourse())
                || !moduleEntity.getTitle().equals(command.getTitle())
                || !moduleEntity.getQuizId().equals(command.getQuizId())) {
            moduleEntity.setTitle(command.getTitle());
            moduleEntity.setSerialNumber(command.getOrder());
            moduleEntity.setDescription(command.getDescription());
            moduleEntity.setLessonsId(command.getLessonsId());
            moduleEntity.setCourse(command.getCourse());
            moduleEntity.setUpdatedAt((Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
        }
        return moduleRepository.save(moduleEntity);
    }

    public List<Module> getAll() {
        return moduleRepository.findAll();
    }

    public List<Integer> getLessonsIdFromModule(Long id) {
        Module module = moduleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return module.getLessonsId();
    }

    @Transactional
    public void moveModule(Long moduleId, int newOrder) {
        Optional<Module> moduleOptional = moduleRepository.findById(moduleId);
        if (moduleOptional.isEmpty()) {
            throw new IllegalArgumentException("Module not found");
        }

        Module module = moduleOptional.get();
        Course course = module.getCourse();
        int currentOrder = module.getSerialNumber();

        if (newOrder < 1 || newOrder > moduleRepository.countByCourse(course)) {
            throw new IllegalArgumentException("Invalid order");
        }

        if (newOrder > currentOrder) {
            // Сдвигаем вниз
            List<Module> modules = moduleRepository.findByCourseAndSerialNumberBetween(course, currentOrder + 1, newOrder);
            for (Module m : modules) {
                m.setSerialNumber(m.getSerialNumber() - 1);
                moduleRepository.save(m);
            }
        } else if (newOrder < currentOrder) {
            // Сдвигаем вверх
            List<Module> modules = moduleRepository.findByCourseAndSerialNumberBetween(course, newOrder, currentOrder - 1);
            for (Module m : modules) {
                m.setSerialNumber(m.getSerialNumber() + 1);
                moduleRepository.save(m);
            }
        }

        module.setSerialNumber(newOrder);
        moduleRepository.save(module);
    }
    public List<ModuleResponse> search(ModuleQuery query){
        List<Module> modules = customModuleRepository.search(query);
        return modules.stream().map(i->modelMapper.map(i, ModuleResponse.class)).collect(Collectors.toList());
    }
}
