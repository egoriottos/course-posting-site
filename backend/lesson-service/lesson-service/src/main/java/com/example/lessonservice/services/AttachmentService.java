package com.example.lessonservice.services;

import com.example.lessonservice.commands.Attachment.AttachmentSearch;
import com.example.lessonservice.commands.Attachment.CreateAttachmentCommand;
import com.example.lessonservice.commands.Attachment.UpdateAttachmentCommand;
import com.example.lessonservice.commands.Attachment.response.AttachmentResponse;
import com.example.lessonservice.entities.Attachment;
import com.example.lessonservice.repository.AttachmentRepository;
import com.example.lessonservice.repository.CustomRepository.CustomAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private static final String uploadDIR = "C:\\Users\\titko\\OneDrive\\Рабочий стол\\folder"; //todo
    private final CustomAttachmentRepository customAttachmentRepository;
    private final AttachmentRepository attachmentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void create(MultipartFile file, CreateAttachmentCommand createAttachmentCommand) throws IOException { // загрузить вложение
       String fileName = file.getOriginalFilename();
       String filePath = uploadDIR + File.separator + fileName;

       File dest = new File(filePath);
       file.transferTo(dest);
       Attachment attachment = new Attachment();
       attachment.setFileName(createAttachmentCommand.getFileName());
       attachment.setUrl(filePath);
       attachment.setFileType(createAttachmentCommand.getFileType());
       attachment.setLesson(createAttachmentCommand.getLesson());
       attachment.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

       attachmentRepository.save(attachment);
    }

    public List<AttachmentResponse> search(AttachmentSearch search){
        List<Attachment> attachments = customAttachmentRepository.search(search);
        return attachments.stream().map(l->modelMapper.map(l, AttachmentResponse.class)).toList();
    }
    @Transactional
    public void delete(Long id) {
        attachmentRepository.deleteById(id);
    }
    @Transactional
    public void update(Long id, MultipartFile file, UpdateAttachmentCommand command) throws IOException {

        Optional<Attachment> attachmentOpt = attachmentRepository.findById(id);
        if (attachmentOpt.isPresent()) {
            Attachment attachment = attachmentOpt.get();

            // Удаление старого файла, если он существует
            File oldFile = new File(attachment.getUrl());
            if (oldFile.exists() && !oldFile.delete()) {
                throw new IOException("Failed to delete old file: " + oldFile.getPath());
            }

            // Создание нового файла
            String fileName = file.getOriginalFilename();
            String fileType = file.getContentType();
            if (fileName == null || fileType == null) {
                throw new IllegalArgumentException("File name and type must not be null");
            }

            // Генерация уникального имени файла, чтобы избежать конфликтов
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            String filePath = uploadDIR + File.separator + uniqueFileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            // Обновление информации о файле
            attachment.setFileName(uniqueFileName);
            attachment.setFileType(fileType);
            attachment.setUrl(filePath);
            attachment.setLesson(command.getLesson());
            attachment.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

            attachmentRepository.save(attachment);
        } else {
            throw new RuntimeException("Attachment not found with id: " + id);
        }
    }
}
