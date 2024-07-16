package com.example.lessonservice.commands.Attachment.attachmentController;

import com.example.lessonservice.commands.Attachment.AttachmentSearch;
import com.example.lessonservice.commands.Attachment.CreateAttachmentCommand;
import com.example.lessonservice.commands.Attachment.UpdateAttachmentCommand;
import com.example.lessonservice.commands.Attachment.attachmentQuery.AttachmentQuery;
import com.example.lessonservice.commands.Attachment.response.AttachmentResponse;
import com.example.lessonservice.entities.Attachment;
import com.example.lessonservice.services.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public AttachmentResponse create(@RequestParam MultipartFile file, @RequestBody CreateAttachmentCommand command) throws IOException {
       return modelMapper.map(attachmentService.create(file,command),AttachmentResponse.class);
    }
    @PostMapping("/search")
    public List<AttachmentResponse> search(@RequestBody AttachmentSearch search){
        return attachmentService.search(search);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        attachmentService.delete(id);
        return "Attachment was Deleted Successfully " + id;
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,@RequestParam MultipartFile file, @RequestBody UpdateAttachmentCommand command) throws IOException {
        attachmentService.update(id, file, command);
        return "Attachment was Updated Successfully " + id+ " " + file.getOriginalFilename()
                + " " + file.getSize()+ " " + command.getFileName() + " " + command.getUrl() + " " + command.getLesson();
    }

}
