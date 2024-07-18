package com.example.sertificateservice.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CertificateDto {
    private Long courseId;
    private String certificateUrl; //Хранение URL сертификата выданного
    private String templateUrl; // Хранение URL шаблона (если нужно)
    private String templateName; // Название шаблона (если нужно)
    private String firstName; //имя пользователя(не никнейм и не логин)
    private String lastName; // фамилия
    private String patronymicName; // отчество
    private String courseName; // Название курса для сертификата
    private Date issuedAt; // дата выдачи
    private Date createdAt;
    private Date updatedAt;
}
