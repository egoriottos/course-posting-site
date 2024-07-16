package com.example.lessonservice.commands.Content;

import com.example.lessonservice.entities.Lesson;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentSearch {
    public ContentSearch(@JsonProperty("id") Long id, @JsonProperty("lesson") Lesson lesson,
                         @JsonProperty("data") String data, @JsonProperty("url") String url,
                         @JsonProperty("createdAt") Date createdAt, @JsonProperty("updatedAt") Date updatedAt,
                         @JsonProperty("limit")Integer limit, @JsonProperty("offset")Integer offset) {
        this.id = id;
        this.lesson = lesson;
        this.data = data;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = 15;
        this.offset = 0;
    }

    private Long id;

    private Lesson lesson;

    private String data;

    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updatedAt;

    private Integer limit;

    private Integer offset;
}
