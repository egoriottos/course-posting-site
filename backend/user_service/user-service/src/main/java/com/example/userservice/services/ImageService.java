package com.example.userservice.services;

import com.example.userservice.commands.UpdateImageCommand;
import com.example.userservice.domain.entity.Image;
import com.example.userservice.repository.interfaces.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    public Image save(String directory, MultipartFile file) throws IOException {
        // Создаем путь к файлу
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(directory, fileName);

        // Создаем директории, если они не существуют
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        // Сохраняем файл по указанному пути
        Files.write(path, file.getBytes());

        // Создаем и сохраняем объект Image в базу данных
        Image image = new Image(path.toString());
        return imageRepository.save(image);
    }
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image updateImage(Long id, String directory, MultipartFile file) throws IOException {
        // Находим существующее изображение в базе данных
        Optional<Image> existingImageOpt = imageRepository.findById(id);
        if (!existingImageOpt.isPresent()) {
            throw new IOException("Image not found: " + id);
        }

        Image existingImage = existingImageOpt.get();

        // Удаляем старый файл (необязательно)
        Path oldPath = Paths.get(existingImage.getProfileImageUrl());
        if (Files.exists(oldPath)) {
            Files.delete(oldPath);
        }

        // Сохраняем новый файл
        String fileName = file.getOriginalFilename();
        Path newPath = Paths.get(directory, fileName);

        if (!Files.exists(newPath.getParent())) {
            Files.createDirectories(newPath.getParent());
        }

        Files.write(newPath, file.getBytes());

        // Обновляем путь к файлу в базе данных
        existingImage.setProfileImageUrl(newPath.toString());
        return imageRepository.save(existingImage);
    }

}
