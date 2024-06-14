package com.example.userservice.services;

import com.example.userservice.domain.entity.Teacher;
import com.example.userservice.query.SearchTeacherQuery;
import com.example.userservice.repository.CustomTeacherRepository;
import com.example.userservice.repository.interfaces.TeacherRepository;
import com.example.userservice.responses.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeacherService {
    private final CustomTeacherRepository customTeacherRepository;
    private final ModelMapper modelMapper;

    public List<TeacherResponse> search(SearchTeacherQuery searchTeacherQuery){
        List<Teacher> teachers = customTeacherRepository.search(searchTeacherQuery);
        return teachers.stream().map(t->modelMapper.map(t, TeacherResponse.class)).toList();
    }

}
