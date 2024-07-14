package com.example.userservice.responses;

import com.example.userservice.domain.entity.User;
import lombok.Data;

@Data
public class ImageResponse {
    private String url;
    private Long id;
    private User owner;
}
