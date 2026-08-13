package com.substring.auth.mapper;

import com.substring.auth.dtos.UserDto;
import com.substring.auth.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
