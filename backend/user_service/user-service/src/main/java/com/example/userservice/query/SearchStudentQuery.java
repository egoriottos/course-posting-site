package com.example.userservice.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class SearchStudentQuery {
    private Long id;
    private Long ownerId;
    private List<Integer> quizResultsId;
    private List<Integer> coursesId;
    private Integer limit;
    private Integer offset;
}
