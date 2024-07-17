package com.example.quizservice.application.services;

import com.example.quizservice.commands.image.SearchImageParams;
import com.example.quizservice.commands.image.dto.ImageDto;
import com.example.quizservice.domain.entity.Image;
import com.example.quizservice.repositories.ImageRepository;
import com.example.quizservice.repositories.customRepositories.CustomImageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final CustomImageRepository customImageRepository;
    private final ModelMapper modelMapper;
    //создаем изображение
    public void create(String directory, MultipartFile file) throws IOException {
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
        imageRepository.save(image);
    }
    //получаем изображение по айди
    public ImageDto getImageById(Long id) {
       return modelMapper.map( imageRepository.findById(id), ImageDto.class);
    }
    //получаем все изображения
    public List<ImageDto> getAllImages() {
        return imageRepository.findAll().
                stream().map(image -> modelMapper.map(image, ImageDto.class)).collect(Collectors.toList());
    }
    //обновляем картинку , предварительно найдя ее по айди
    public ImageDto updateImageById(Long id, String directory, MultipartFile file) throws IOException {
        // Находим существующее изображение в базе данных
        Optional<Image> existingImageOpt = imageRepository.findById(id);
        if (!existingImageOpt.isPresent()) {
            throw new IOException("Image not found: " + id);
        }

        Image existingImage = existingImageOpt.get();

        // Удаляем старый файл (необязательно)
        Path oldPath = Paths.get(existingImage.getUrl());
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
        existingImage.setUrl(newPath.toString());
        return modelMapper.map(imageRepository.save(existingImage), ImageDto.class);
    }
    //удаляем по айди картинку
    public void deleteImageById(Long id) {
        imageRepository.deleteById(id);
    }
    //нахожу все картинки которые находятся в одном вопросе(предварительно их может быть больше одной
    public List<ImageDto> getAllImagesByQuestionId(Long id) {
        return imageRepository.findAllByQuestionId(id).stream()
                .map(object -> modelMapper.map(object, ImageDto.class)).collect(Collectors.toList());
    }
    //поиск картинок по одному/нескольким параметрам возвращает до 15 результатов
    public List<ImageDto> search(SearchImageParams params){
        return customImageRepository.search(params)
                .stream().map(object -> modelMapper.map(object, ImageDto.class)).collect(Collectors.toList());
    }

}
