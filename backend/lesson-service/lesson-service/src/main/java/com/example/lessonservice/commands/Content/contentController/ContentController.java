package com.example.lessonservice.commands.Content.contentController;

import com.example.lessonservice.commands.Content.contentQuery.ContentQuery;
import com.example.lessonservice.entities.Content;
import com.example.lessonservice.enums.ContentType;
import com.example.lessonservice.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;
    private final ModelMapper modelMapper;

    @GetMapping("/get/{id}")
    public ContentQuery getById(@PathVariable Long id) {
        Content content = contentService.getContentById(id);
        return modelMapper.map(content, ContentQuery.class);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ContentQuery>> getAll() {
        List<Content> contentList = contentService.getAllContents();
        List<ContentQuery> contentQueries = contentList.stream().map(obj->modelMapper.map(obj, ContentQuery.class)).toList();
        return ResponseEntity.ok(contentQueries);
    }

    @PostMapping("/add")
    public ResponseEntity<ContentQuery> add(@RequestBody ContentQuery content) {
        Content addContent = contentService.addContent(modelMapper.map(content, Content.class));
        return ResponseEntity.ok(modelMapper.map(addContent, ContentQuery.class));
    }

    @PutMapping("/update/content/{id}")
    public ResponseEntity<ContentQuery> update(@PathVariable Long id, @RequestBody ContentQuery content) {
        Content contentToUpdate = contentService.updateContentById(id, modelMapper.map(content, Content.class));
        return ResponseEntity.ok(modelMapper.map(contentToUpdate, ContentQuery.class));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        contentService.deleteContentById(id);
        return "Content was successfully deleted";
    }

    @GetMapping("/get/byLesson/{id}")
    public List<ContentQuery> getByLesson(@PathVariable Long id) {
        List<Content> contentList = contentService.getContentsByLessonId(id);
        return contentList.stream().map(obj->modelMapper.map(obj, ContentQuery.class)).toList();
    }

    @GetMapping("get/byType")
    public List<ContentQuery> getByType(@RequestBody ContentType type) {
        List<Content> contentList = contentService.getContentsByType(type);
        return contentList.stream().map(obj->modelMapper.map(obj, ContentQuery.class)).toList();
    }
}
