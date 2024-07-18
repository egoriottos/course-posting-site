package com.example.sertificateservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "certificate")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
//поля которые надо будет заполнить, такие как имя фамилия, отчество, дата выдачи, будут динамическими, используется
//доп библиотека itext-core
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Date updatedAt; // добавлено поле в случае если не те поля внесутся в сертификат и придется обновлять
}
