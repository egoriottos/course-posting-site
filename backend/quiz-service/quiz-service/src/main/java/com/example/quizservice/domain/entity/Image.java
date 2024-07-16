package com.example.quizservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    public Image(String path) {
        this.url = path;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    @ManyToOne
    private Question question;
}
