package com.israel.api_blog.role.service;

import com.israel.api_blog.role.dto.request.CreateRoleRequestDto;
import com.israel.api_blog.role.dto.request.UpdateRoleRequestDto;
import com.israel.api_blog.role.dto.response.RoleResponseDto;

import java.util.List;

public interface RoleService {
    RoleResponseDto getRoleById(Long roleId);

    List<RoleResponseDto> getRoles();

    RoleResponseDto createRole(CreateRoleRequestDto createRoleRequestDto);

    RoleResponseDto updateRole(Long roleId, UpdateRoleRequestDto updateRoleRequestDto);

    void deleteRole(Long roleId);
}
