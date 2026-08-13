package com.substring.auth.services.impl;

import com.substring.auth.dtos.UserDto;
import com.substring.auth.entities.User;
import com.substring.auth.exceptions.ResourceNotFoundException;
import com.substring.auth.helpers.UserHelper;
import com.substring.auth.mapper.UserMapper;
import com.substring.auth.repositories.UserRepository;
import com.substring.auth.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {

        if (userDto.getEmail() == null || userDto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is required");
        }
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }



        //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userMapper.toEntity(userDto);
        try {
            return userMapper.toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating user", e);
        }
    }

    @Override
    public UserDto getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + email));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) throws Exception {
        User user = userRepository.findById(UserHelper.parseUUID(userId))
                .orElseThrow(()-> new Exception("User not found with id: " + userId));

        if (userDto.getEmail() != null && !userDto.getEmail().isBlank()){
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null && !userDto.getName().isBlank()){
            user.setName(userDto.getName());
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()){
            //if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
                throw new IllegalArgumentException("Password does not match");
            //}
            //user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        }
        if (userDto.getImage() != null && !userDto.getImage().isBlank()){
            user.setImage(userDto.getImage());
        }

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        User user = userRepository.findById(UserHelper.parseUUID(userId))
                .orElseThrow(()-> new Exception("User not found with id: " + userId));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(String userId) throws Exception {
        User user = userRepository.findById(UserHelper.parseUUID(userId))
                .orElseThrow(()-> new Exception("User not found with id: " + userId));
        return userMapper.toDto(user);
    }

    @Override
    public Iterable<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
