package com.example.userservice.controllers;

import com.example.userservice.domain.entity.Image;
import com.example.userservice.query.requests.ImageRequest;
import com.example.userservice.responses.ImageResponse;
import com.example.userservice.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<ImageResponse> getAllImages() {
       return imageService.getAllImages().stream().
               map(i -> modelMapper.map(i, ImageResponse.class)).collect(Collectors.toList());
    }
    @GetMapping("get/{id}")
    public ImageResponse getImageById(@PathVariable Long id) {
        return modelMapper.map(imageService.getImageById(id), ImageResponse.class);
    }
    @PostMapping("/create")
    public ResponseEntity<ImageResponse> createImage(@RequestParam String directory, @RequestParam MultipartFile file) {
        try {
            Image savedImage = imageService.save(directory, file);
            return new ResponseEntity<>(modelMapper.map(savedImage,ImageResponse.class), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ImageResponse updateImage(@PathVariable Long id, @RequestParam String directory, @RequestParam MultipartFile file) {
        try {
           return modelMapper.map(imageService.updateImage(id,directory,file), ImageResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return "Image deleted";
    }

}
