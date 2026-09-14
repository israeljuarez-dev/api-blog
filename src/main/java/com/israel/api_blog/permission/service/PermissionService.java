package com.israel.api_blog.permission.service;

import com.israel.api_blog.permission.dto.request.CreatePermissionRequestDto;
import com.israel.api_blog.permission.dto.request.UpdatePermissionRequestDto;
import com.israel.api_blog.permission.dto.response.PermissionResponseDto;

import java.util.List;

public interface PermissionService {
    PermissionResponseDto getPermissionById(Long permissionId);

    List<PermissionResponseDto> getPermissions();

    PermissionResponseDto createPermission(CreatePermissionRequestDto createPermissionRequestDto);

    PermissionResponseDto updatePermission(Long permissionId, UpdatePermissionRequestDto updatePermissionRequestDto);

    void deletePermission(Long permissionId);
}
