package com.example.sertificateservice.services;

import com.example.sertificateservice.domain.entity.Certificate;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class CertificateGenerationService {
    public byte[] generateCertificate(Certificate certificate) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfReader reader = new PdfReader(certificate.getTemplateUrl());
             PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDoc = new PdfDocument(reader, writer)) {

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
            form.getField("имя").setValue(certificate.getFirstName());
            form.getField("фамилия").setValue(certificate.getLastName());
            form.getField("отчество").setValue(certificate.getPatronymicName());
            form.getField("название курса").setValue(certificate.getCourseName());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            String formattedDate = LocalDateTime.now().format(formatter);
            form.getField("Дата выдачи").setValue(formattedDate);

            form.flattenFields();
        }

        return outputStream.toByteArray();
    }
}
