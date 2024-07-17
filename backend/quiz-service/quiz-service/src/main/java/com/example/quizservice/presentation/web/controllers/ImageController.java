package com.example.quizservice.presentation.web.controllers;

import com.example.quizservice.application.services.ImageService;
import com.example.quizservice.commands.image.SearchImageParams;
import com.example.quizservice.commands.image.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final ModelMapper modelMapper;
    @PostMapping("/create")
    public String createImage(String directory, MultipartFile file) throws IOException {
        imageService.create(directory, file);
        return "Image created " + file.getOriginalFilename();
    }
    @GetMapping("/get/{id}")
    public ImageResponse getImage(@PathVariable long id) {
       var imageDto = imageService.getImageById(id);
       return modelMapper.map(imageDto, ImageResponse.class);
    }
    @PutMapping("/update/{imageId}")
    public String updateImage(@PathVariable long imageId, String directory,MultipartFile file)
            throws IOException {
        imageService.updateImageById(imageId, directory, file);
        return "Image updated " + file.getOriginalFilename()+ " with id " + imageId;
    }
    @DeleteMapping("/delete/{imageId}")
    public String deleteImage(@PathVariable long imageId) {
        imageService.deleteImageById(imageId);
        return "Image deleted with id " + imageId;
    }
    @PostMapping("/search")
    public List<ImageResponse> searchImages(@RequestBody SearchImageParams params) {
        return imageService.search(params).stream()
                .map(obj -> modelMapper.map(obj, ImageResponse.class)).collect(Collectors.toList());
    }
}
