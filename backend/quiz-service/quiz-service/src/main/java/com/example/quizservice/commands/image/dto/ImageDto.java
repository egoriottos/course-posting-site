package com.example.quizservice.commands.image.dto;

import com.example.quizservice.domain.entity.Question;
import lombok.Data;

@Data
public class ImageDto {

    private Long id;

    private String url;

    private Question question;
}
