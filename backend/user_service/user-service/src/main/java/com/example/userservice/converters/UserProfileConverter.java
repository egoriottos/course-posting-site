package com.example.userservice.converters;

import com.example.userservice.domain.entity.User;
import com.example.userservice.domain.entity.UserProfile;
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
public class UserProfileConverter implements AttributeConverter<UserProfile, String> {
    private final ObjectMapper mapper;
    @Override
    public String convertToDatabaseColumn(UserProfile userProfile) {
        try {
            return mapper.writeValueAsString(userProfile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert userProfile to JSON", e);
        }
    }

    @Override
    public UserProfile convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, new TypeReference<UserProfile>() {
                @Override
                public Type getType() {
                    TypeToken<UserProfile> typeToken = new TypeToken<UserProfile>() {
                    };

                    return typeToken.getType();
                }
            });
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Не удалось преобразовать строку в объект. " + s, exception);
        }
    }
}
