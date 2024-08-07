package com.example.quizservice.commands.image.response;

import com.example.quizservice.domain.entity.Question;
import lombok.Data;

@Data
public class ImageResponse {
    private Long id;
    private String url;
    private Question question;
}
