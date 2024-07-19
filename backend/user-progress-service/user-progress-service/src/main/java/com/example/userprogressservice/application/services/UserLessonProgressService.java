package com.example.userprogressservice.application.services;

import com.example.userprogressservice.repository.UserLessonProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLessonProgressService {
    private final UserLessonProgressRepository userLessonProgressRepository;
}
