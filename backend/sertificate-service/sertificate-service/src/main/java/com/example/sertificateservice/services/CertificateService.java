package com.example.sertificateservice.services;

import com.example.sertificateservice.commands.CreateCertificateCommand;
import com.example.sertificateservice.commands.UpdateCertificateCommand;
import com.example.sertificateservice.domain.entity.Certificate;
import com.example.sertificateservice.dto.CertificateDto;
import com.example.sertificateservice.repository.CertificateRepository;
import com.example.sertificateservice.repository.CustomCertificateRepository;
import com.example.sertificateservice.repository.params.SearchParams;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final CustomCertificateRepository customCertificateRepository;
    private final ModelMapper modelMapper;

    //создание сертификата
    @Transactional
    public void createCertificate(CreateCertificateCommand command){
        var certificate = Certificate.builder()
                .courseId(command.getCourseId())
                .certificateUrl(command.getCertificateUrl())
                .templateUrl(command.getTemplateUrl())
                .templateName(command.getTemplateName())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .patronymicName(command.getPatronymicName())
                .courseName(command.getCourseName())
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .issuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        certificateRepository.save(certificate);
    }
    //обновление сертификата
    @Transactional
    public void updateCertificate(Long id,UpdateCertificateCommand command){
        var certificate = certificateRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Certificate with id " + id + " not found"));
        if(!certificate.getCourseId().equals(command.getCourseId())
                || !certificate.getCertificateUrl().equals(command.getCertificateUrl())
                || !certificate.getTemplateUrl().equals(command.getTemplateUrl())
                || !certificate.getTemplateName().equals(command.getTemplateName())
                || !certificate.getFirstName().equals(command.getFirstName())
                || !certificate.getLastName().equals(command.getLastName())
                || !certificate.getPatronymicName().equals(command.getPatronymicName())
                || !certificate.getCourseName().equals(command.getCourseName())
                || !certificate.getIssuedAt().equals(command.getIssuedAt()))
        {
            certificate.setCourseId(command.getCourseId());
            certificate.setCertificateUrl(command.getCertificateUrl());
            certificate.setTemplateUrl(command.getTemplateUrl());
            certificate.setTemplateName(command.getTemplateName());
            certificate.setFirstName(command.getFirstName());
            certificate.setLastName(command.getLastName());
            certificate.setPatronymicName(command.getPatronymicName());
            certificate.setCourseName(command.getCourseName());
            certificate.setIssuedAt(command.getIssuedAt());
            certificate.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            certificateRepository.save(certificate);
        }
    }
    //нахождение по айди
    public CertificateDto getCertificate(Long id){
        return modelMapper.map(certificateRepository.findById(id),CertificateDto.class);
    }
    //удаление по айди
    @Transactional
    public void deleteCertificate(Long id){
        certificateRepository.deleteById(id);
    }
    //получение всех
    public List<CertificateDto> getAllCertificates(){
       return certificateRepository.findAll().stream()
               .map(obj->modelMapper.map(obj,CertificateDto.class)).collect(Collectors.toList());
    }
    //поиск по одному/нескольким параметрам
    public List<CertificateDto> search(SearchParams params){
        return customCertificateRepository.search(params).stream()
                .map(obj->modelMapper.map(obj,CertificateDto.class)).collect(Collectors.toList());
    }
}
