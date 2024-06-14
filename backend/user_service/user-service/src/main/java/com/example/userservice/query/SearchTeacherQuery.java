package com.example.userservice.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class SearchTeacherQuery {
    private Long id;
    private Long ownerId;
    private List<Integer> coursesId;
    private Float rating;
    private Integer limit;
    private Integer offset;
}
