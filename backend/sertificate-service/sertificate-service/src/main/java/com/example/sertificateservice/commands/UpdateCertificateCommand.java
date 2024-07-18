package com.example.sertificateservice.commands;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateCertificateCommand {
    private Long courseId;
    private String certificateUrl; //Хранение URL сертификата выданного
    private String templateUrl; // Хранение URL шаблона (если нужно)
    private String templateName; // Название шаблона (если нужно)
    private String firstName; //имя пользователя(не никнейм и не логин)
    private String lastName; // фамилия
    private String patronymicName; // отчество
    private String courseName; // Название курса для сертификата
    private Date issuedAt; // дата выдачи
}
