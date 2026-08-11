package com.substring.auth.services;

import com.substring.auth.dtos.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByEmail(String email) throws Exception;

    UserDto updateUser(UserDto userDto, String userId) throws Exception;

    void deleteUser(String userId) throws Exception;

    UserDto getUserById(String userId) throws Exception;

    Iterable<UserDto> getAllUser();
}
