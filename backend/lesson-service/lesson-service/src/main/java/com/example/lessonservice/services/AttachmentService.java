package com.example.lessonservice.services;

import com.example.lessonservice.entities.Attachment;
import com.example.lessonservice.repository.AttachmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private static final String uploadDIR = "C:\\Users\\titko\\OneDrive\\Рабочий стол\\folder"; //todo

    private final AttachmentRepository attachmentRepository;

    public Attachment upload(Attachment attachment)  { // загрузить вложение
//       String fileName = file.getOriginalFilename();
//       String fileType = file.getContentType();
//       String filePath = uploadDIR + File.separator + fileName;
//
//       File dest = new File(filePath);
//       file.transferTo(dest);
//       Attachment attachment = new Attachment();
//       attachment.setFileName(fileName);
//       attachment.setUrl(filePath);
//       attachment.setFileType(fileType);

       return attachmentRepository.save(attachment);
    }

    public List<Attachment> findAll() {
        return attachmentRepository.findAll();
    }

    public Attachment findOne(Long id) {
        return attachmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Attachment with id " + id + " not found"));
    }

    public void delete(Long id) {
        attachmentRepository.deleteById(id);
    }

    public List<Attachment> findByLesson(Long lessonId) {
        return attachmentRepository.findByLessonId(lessonId);
    }

    public Attachment update(Long id,Attachment attach) {
        var attachment = findOne(id);
        if (attachment != null && (!attachment.getUrl().equals(attach.getUrl())
                || !attachment.getFileName().equals(attach.getFileName())
                || !attachment.getFileType().equals(attach.getFileType())
                ||!attachment.getLesson().equals(attach.getLesson())))
        {
            attachment.setLesson(attach.getLesson());
            attachment.setUrl(attach.getUrl());
            attachment.setFileName(attach.getFileName());
            attachment.setFileType(attach.getFileType());
            return attachmentRepository.save(attachment);
        }
        else {
            throw  new EntityNotFoundException("Attachment cannot be updated because not exist");
        }
//        Optional<Attachment> attachmentOpt = attachmentRepository.findById(id);
//        if (attachmentOpt.isPresent()) {
//            Attachment attachment = attachmentOpt.get();
//
//            File oldFile = new File(attachment.getUrl());
//            if (oldFile.exists()) {
//                oldFile.delete();
//            }
//
//            String fileName = file.getOriginalFilename();
//            String fileType = file.getContentType();
//            String filePath = uploadDIR + File.separator + fileName;
//            File dest = new File(filePath);
//            file.transferTo(dest);
//
//            attachment.setFileName(fileName);
//            attachment.setFileType(fileType);
//            attachment.setUrl(filePath);
//
//            return attachmentRepository.save(attachment);
//        } else {
//            throw new RuntimeException("Attachment not found");
//        }
    }
}
