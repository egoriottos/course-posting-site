package com.example.userservice.controllers;

import com.example.userservice.query.SearchTeacherQuery;
import com.example.userservice.responses.TeacherResponse;
import com.example.userservice.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/search")
    public List<TeacherResponse> search(@RequestBody SearchTeacherQuery searchTeacherQuery){
        return teacherService.search(searchTeacherQuery);
    }

}
