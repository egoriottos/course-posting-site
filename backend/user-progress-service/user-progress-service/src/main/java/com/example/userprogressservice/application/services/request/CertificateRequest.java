package com.example.userprogressservice.application.services.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificateRequest {

    private Long userId;
    private Long courseId;
}
