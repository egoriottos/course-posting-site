package com.example.userservice.services;

import com.example.userservice.commands.CreateUserCommand;
import com.example.userservice.commands.UpdateUserCommand;
import com.example.userservice.domain.entity.User;
import com.example.userservice.query.SearchParams;
import com.example.userservice.query.requests.RequestUserData;
import com.example.userservice.repository.CustomUserRepository;
import com.example.userservice.repository.interfaces.UserRepository;
import com.example.userservice.responses.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;
    private final ModelMapper modelMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createUser(RequestUserData createUserCommand) {
        var user = User.builder()
                .login(createUserCommand.getLogin())
                .email(createUserCommand.getEmail())
                .password(createUserCommand.getPassword())
                .firstname(createUserCommand.getFirstname())
                .lastname(createUserCommand.getLastname())
                .phone(createUserCommand.getPhone())
                .dateOfBirth(createUserCommand.getDateOfBirth())
                .roles(createUserCommand.getRoles())
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                        .build();
        userRepository.save(user);
    }

    public List<UserResponse> search(SearchParams params){
        List<User> users =customUserRepository.search(params);
        return users.stream().map(obj->modelMapper.map(obj, UserResponse.class)).toList();
    }
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    @Transactional
    public UserResponse update(Long id,UpdateUserCommand updateUserCommand){
        var user = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found"));
        if(!user.getLogin().equals(updateUserCommand.getLogin())
                || !user.getEmail().equals(updateUserCommand.getEmail())
                || !user.getPassword().equals(updateUserCommand.getPassword())
                || !user.getFirstname().equals(updateUserCommand.getFirstname())
                || !user.getLastname().equals(updateUserCommand.getLastname())
                || !user.getPhone().equals(updateUserCommand.getPhone())
                || !user.getDateOfBirth().equals(updateUserCommand.getDateOfBirth()))
        {
            user.setLogin(updateUserCommand.getLogin());
            user.setEmail(updateUserCommand.getEmail());
            user.setPassword(updateUserCommand.getPassword());
            user.setFirstname(updateUserCommand.getFirstname());
            user.setLastname(updateUserCommand.getLastname());
            user.setPhone(updateUserCommand.getPhone());
            user.setDateOfBirth(updateUserCommand.getDateOfBirth());
            user.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        }
        return modelMapper.map(userRepository.save(user),UserResponse.class);
    }

}
