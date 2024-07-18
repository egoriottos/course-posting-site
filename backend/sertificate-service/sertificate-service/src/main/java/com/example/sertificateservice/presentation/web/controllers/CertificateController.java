package com.example.sertificateservice.presentation.web.controllers;

import com.example.sertificateservice.commands.CreateCertificateCommand;
import com.example.sertificateservice.commands.UpdateCertificateCommand;
import com.example.sertificateservice.repository.params.SearchParams;
import com.example.sertificateservice.response.CertificateResponse;
import com.example.sertificateservice.services.CertificateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class CertificateController {
    private final CertificateService certificateService;
    private final ModelMapper modelMapper;

    @GetMapping("/getAll")
    public List<CertificateResponse> getAll(){
        return certificateService.getAllCertificates().stream()
                .map(obj->modelMapper.map(obj, CertificateResponse.class)).collect(Collectors.toList());
    }
    @GetMapping("/getById/{id}")
    public CertificateResponse getById(@PathVariable Long id){
        return modelMapper.map(certificateService.getCertificate(id), CertificateResponse.class);
    }
    @PostMapping("/create")
    public String create(@RequestBody CreateCertificateCommand command){
        certificateService.createCertificate(command);
        return "Certificate was created " +command.getCertificateUrl() + command.getTemplateName();
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return "Certificate was successfully deleted " + id;
    }
    @PostMapping("/search")
    public List<CertificateResponse> search(@RequestBody SearchParams params){
        return certificateService.search(params).stream()
                .map(obj->modelMapper.map(obj, CertificateResponse.class)).collect(Collectors.toList());
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody UpdateCertificateCommand command) {
        certificateService.updateCertificate(id, command);
        return "Certificate was updated " + command.getCertificateUrl() +" " + id;
    }
}
