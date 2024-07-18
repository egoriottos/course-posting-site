package com.example.sertificateservice.repository.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchParams {
    public SearchParams(@JsonProperty("id") Long id,@JsonProperty("courseId") Long courseId,
                       @JsonProperty("certificateUrl") String certificateUrl,@JsonProperty("templateUrl") String templateUrl,
                       @JsonProperty("templateName") String templateName,@JsonProperty("firstName") String firstName,
                       @JsonProperty("lastName") String lastName,@JsonProperty("patronymicName") String patronymicName,
                       @JsonProperty("courseName") String courseName,@JsonProperty("issuedAt") Date issuedAt,
                       @JsonProperty("createdAt") Date createdAt,@JsonProperty("updatedAt") Date updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.certificateUrl = certificateUrl;
        this.templateUrl = templateUrl;
        this.templateName = templateName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.courseName = courseName;
        this.issuedAt = issuedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = 15;
        this.offset = 0;
    }

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
    private Date updatedAt;
    private Integer limit;
    private Integer offset;
}
