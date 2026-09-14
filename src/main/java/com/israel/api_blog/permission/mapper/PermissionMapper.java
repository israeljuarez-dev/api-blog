package com.israel.api_blog.permission.mapper;

import com.israel.api_blog.permission.dto.request.CreatePermissionRequestDto;
import com.israel.api_blog.permission.dto.response.PermissionResponseDto;
import com.israel.api_blog.permission.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponseDto toPermissionResponseDto(Permission permission);

    Permission toPermission(CreatePermissionRequestDto createPermissionRequestDto);
}
