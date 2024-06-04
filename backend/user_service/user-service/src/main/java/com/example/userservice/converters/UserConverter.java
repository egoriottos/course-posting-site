package com.example.userservice.converters;

import com.example.userservice.domain.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Converter(autoApply = true)
@Component
@RequiredArgsConstructor
public class UserConverter implements AttributeConverter<User, String> {
    private final ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(User user) {
        try {
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert user to JSON", e);
        }
    }

    @Override
    public User convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, new TypeReference<User>() {
                @Override
                public Type getType() {
                    TypeToken<User> typeToken = new TypeToken<User>() {
                    };

                    return typeToken.getType();
                }
            });
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Не удалось преобразовать строку в объект. " + dbData);
        }
    }
}
