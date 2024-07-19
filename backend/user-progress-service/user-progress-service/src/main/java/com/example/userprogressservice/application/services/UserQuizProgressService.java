package com.example.userprogressservice.application.services;

import com.example.userprogressservice.repository.UserQuizProgressRepository;
import com.example.userprogressservice.repository.customRepository.CustomQuizProgressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQuizProgressService {
    private final UserQuizProgressRepository userQuizProgressRepository;
    private final CustomQuizProgressRepository quizProgressRepository;
    private final ModelMapper modelMapper;

}
