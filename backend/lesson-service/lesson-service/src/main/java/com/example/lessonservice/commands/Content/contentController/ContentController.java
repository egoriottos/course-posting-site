package com.example.lessonservice.commands.Content.contentController;

import com.example.lessonservice.commands.Content.ContentSearch;
import com.example.lessonservice.commands.Content.CreateContentCommand;
import com.example.lessonservice.commands.Content.UpdateContentCommand;
import com.example.lessonservice.commands.Content.response.ContentResponse;
import com.example.lessonservice.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class ContentController {
    private final ContentService contentService;

    @PostMapping("/create")
    public String create(@RequestBody CreateContentCommand createContentCommand) {
        contentService.create(createContentCommand);
        return "Content created successfully" + createContentCommand.getUrl()+ " "+ createContentCommand.getLesson();

    }

    @PostMapping("/search")
    public List<ContentResponse> search(@RequestBody ContentSearch contentSearch) {
        return contentService.search(contentSearch);
    }

    @PutMapping("/update/content/{id}")
    public String update(@PathVariable Long id, @RequestBody UpdateContentCommand content) {
        contentService.updateContentById(id, content);
        return "Content updated successfully" + content.getUrl()+ " "+ content.getLesson();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        contentService.deleteContentById(id);
        return "Content was successfully deleted " + id;
    }
}
