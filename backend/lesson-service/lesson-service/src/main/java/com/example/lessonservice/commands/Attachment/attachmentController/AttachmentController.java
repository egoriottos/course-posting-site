package com.example.lessonservice.commands.Attachment.attachmentController;

import com.example.lessonservice.commands.Attachment.attachmentQuery.AttachmentQuery;
import com.example.lessonservice.entities.Attachment;
import com.example.lessonservice.services.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final ModelMapper modelMapper;

    @PostMapping("/upload")
    public AttachmentQuery upload(@RequestBody AttachmentQuery attachmentQuery) {
        Attachment attachmentFromCommand = modelMapper.map(attachmentQuery, Attachment.class);
        Attachment attachment = attachmentService.upload(attachmentFromCommand);
        return modelMapper.map(attachment, AttachmentQuery.class);
    }

    @GetMapping("/all")
    public List<AttachmentQuery> findAll(){
        return attachmentService.findAll().stream().map(obj->modelMapper.map(obj, AttachmentQuery.class)).toList();
    }

    @GetMapping("/get/{id}")
    public AttachmentQuery findOneById(@PathVariable Long id) {
        return modelMapper.map(attachmentService.findOne(id), AttachmentQuery.class);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        attachmentService.delete(id);
        return "Attachment was successfully deleted";
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<List<AttachmentQuery>>findByLessonId(@PathVariable Long id) {
        List<Attachment> attachments = attachmentService.findByLesson(id);
        if (attachments.isEmpty()) {
            return (ResponseEntity.noContent().build());
        }
        return ResponseEntity.ok(attachments.stream().map(obj -> modelMapper.map(obj, AttachmentQuery.class)).toList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttachmentQuery> update(@PathVariable Long id, @RequestBody AttachmentQuery attachmentQuery) {
        Attachment attachmentFromCommand = modelMapper.map(attachmentQuery, Attachment.class);
        Attachment attachment = attachmentService.update(id,attachmentFromCommand);
        AttachmentQuery response = modelMapper.map(attachment, AttachmentQuery.class);
        return ResponseEntity.ok(response);
    }


}
