package org.silogik.securityloginregistartion.mapper;

import org.mapstruct.Mapper;
import org.silogik.securityloginregistartion.dto.RegistrationRequestDto;
import org.silogik.securityloginregistartion.dto.RegistrationResponseDto;
import org.silogik.securityloginregistartion.model.User;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    User toEntity(RegistrationRequestDto registrationRequestDto);

    RegistrationResponseDto toRegistrationResponseDto(User user);
}