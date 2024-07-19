package com.example.userprogressservice.application.services;

import com.example.userprogressservice.repository.UserQuizProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQuizProgressService {
    private final UserQuizProgressRepository userQuizProgressRepository;
}
