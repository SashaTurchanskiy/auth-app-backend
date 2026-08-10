package com.substring.auth.mapper;

import com.substring.auth.dtos.UserDto;
import com.substring.auth.entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
