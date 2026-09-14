package com.israel.api_blog.role.mapper;

import com.israel.api_blog.permission.mapper.PermissionMapper;
import com.israel.api_blog.role.dto.response.RoleResponseDto;
import com.israel.api_blog.role.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PermissionMapper.class)
public interface RoleMapper {

    RoleResponseDto toRoleResponseDto(Role role);
}
