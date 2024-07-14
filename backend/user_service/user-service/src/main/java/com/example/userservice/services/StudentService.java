package com.example.userservice.services;

import com.example.userservice.domain.entity.Student;
import com.example.userservice.query.SearchStudentQuery;
import com.example.userservice.repository.CustomStudentRepository;
import com.example.userservice.repository.interfaces.StudentRepository;
import com.example.userservice.responses.StudentResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CustomStudentRepository customStudentRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    public List<StudentResponse> searchStudent(SearchStudentQuery searchStudentQuery) {
        List<Student> students = customStudentRepository.search(searchStudentQuery);
        return students.stream().map(obj->modelMapper.map(obj,StudentResponse.class)).toList();
    }
    public StudentResponse getStudentById(Long id) {
        return studentRepository.findById(id).map(obj->modelMapper.map(obj,StudentResponse.class)).orElseThrow(()->new EntityNotFoundException("Student not found"));
    }
}
