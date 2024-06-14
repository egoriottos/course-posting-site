package com.example.userservice.controllers;

import com.example.userservice.query.SearchStudentQuery;
import com.example.userservice.responses.StudentResponse;
import com.example.userservice.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/search")
    public List<StudentResponse> searchStudent(@RequestBody SearchStudentQuery searchStudentQuery) {
        return studentService.searchStudent(searchStudentQuery);
    }
}
