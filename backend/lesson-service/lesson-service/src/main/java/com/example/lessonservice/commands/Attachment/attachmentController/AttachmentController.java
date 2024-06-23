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


}
