package com.example.lessonservice.commands.Attachment;

import com.example.lessonservice.entities.Lesson;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentSearch {
    public AttachmentSearch(@JsonProperty("id")Long id,@JsonProperty("lesson") Lesson lesson,
                            @JsonProperty("fileName") String fileName,
                            @JsonProperty("url") String url, @JsonProperty("fileType") String fileType,
                            @JsonProperty("createdAt") Date createdAt, @JsonProperty("updatedAt") Date updatedAt,
                            @JsonProperty("limit") Integer limit, @JsonProperty("offset") Integer offset) {
        this.id = id;
        this.lesson = lesson;
        this.fileName = fileName;
        this.url = url;
        this.fileType = fileType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = limit;
        this.offset = offset;
    }

    private Long id;

    private Lesson lesson;

    private String fileName;

    private String url;

    private String fileType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updatedAt;

    private Integer limit = 15;

    private Integer offset = 0;
}
