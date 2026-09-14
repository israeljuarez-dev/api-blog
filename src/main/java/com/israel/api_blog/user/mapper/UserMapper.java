package com.israel.api_blog.user.mapper;

import com.israel.api_blog.role.mapper.RoleMapper;
import com.israel.api_blog.user.dto.request.RegisterUserRequestDto;
import com.israel.api_blog.user.dto.response.UserResponseDto;
import com.israel.api_blog.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserResponseDto toUserResponseDto(UserEntity user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialNonExpired", ignore = true)
    @Mapping(target = "lastLoginAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(RegisterUserRequestDto registerUserRequestDto);
}
