package com.example.lessonservice.commands.quizSubmission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSubmission {
    private Long quizId;
    private Long userId;
    private Map<Long, String> answers;
    // ключ - айдишник пользователя, а строка- ответ. Нужно для проверки тестов и хренения результатов(возможно)
}
