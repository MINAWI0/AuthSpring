package org.silogik.securityloginregistartion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.silogik.securityloginregistartion.dto.UserProfileDto;
import org.silogik.securityloginregistartion.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    /**
     * Maps User entity to UserDto
     */
    UserProfileDto toDto(User user);

    /**
     * Maps UserDto to User entity
     */
    User toEntity(UserProfileDto userDto);
}