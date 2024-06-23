package com.example.lessonservice.commands.Lesson;

import com.example.lessonservice.entities.Attachment;
import com.example.lessonservice.entities.Content;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonSearch {
    public LessonSearch(@JsonProperty("id") Long id,
                        @JsonProperty("title") String title,
                        @JsonProperty("description") String description,
                        @JsonProperty("contentList") List<Content> contentList,
                        @JsonProperty("attachments") List<Attachment> attachments,
                        @JsonProperty("courseId") Long courseId,
                        @JsonProperty("published") boolean published,
                        @JsonProperty("createdAt") Date createdAt,
                        @JsonProperty("updatedAt") Date updatedAt,
                        @JsonProperty("limit")Integer limit,
                        @JsonProperty("offset")Integer offset) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contentList = contentList;
        this.attachments = attachments;
        this.courseId = courseId;
        this.published = published;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = limit;
        this.offset = offset;
    }

    private Long id;

    private String title;

    private String description;

    private List<Content> contentList;

    private List<Attachment> attachments;

    private Long courseId;

    private boolean published;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updatedAt;

    private Integer limit;

    private Integer offset;
}
