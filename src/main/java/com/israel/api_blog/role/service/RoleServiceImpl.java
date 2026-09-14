package com.israel.api_blog.role.service;

import com.israel.api_blog.permission.model.Permission;
import com.israel.api_blog.permission.repository.PermissionRepository;
import com.israel.api_blog.role.dto.request.CreateRoleRequestDto;
import com.israel.api_blog.role.dto.request.UpdateRoleRequestDto;
import com.israel.api_blog.role.dto.response.RoleResponseDto;
import com.israel.api_blog.role.mapper.RoleMapper;
import com.israel.api_blog.role.model.Role;
import com.israel.api_blog.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDto getRoleById(Long roleId) {
        return roleMapper.toRoleResponseDto(findRoleOrThrow(roleId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponseDto> getRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public RoleResponseDto createRole(CreateRoleRequestDto createRoleRequestDto) {
        Set<Permission> permissions = findPermissionsOrThrow(createRoleRequestDto.permissionIds());

        Role role = Role.builder()
                .name(createRoleRequestDto.name())
                .permissions(permissions)
                .build();

        Role roleSaved = roleRepository.save(role);
        log.info("Rol registrado exitosamente con id {}", roleSaved.getRoleId());

        return roleMapper.toRoleResponseDto(roleSaved);
    }

    @Override
    @Transactional
    public RoleResponseDto updateRole(Long roleId, UpdateRoleRequestDto updateRoleRequestDto) {
        Role role = findRoleOrThrow(roleId);
        Set<Permission> permissions = findPermissionsOrThrow(updateRoleRequestDto.permissionIds());

        role.setName(updateRoleRequestDto.name());
        role.setPermissions(permissions);

        Role roleUpdated = roleRepository.save(role);
        log.info("Rol con id {} actualizado exitosamente", roleUpdated.getRoleId());

        return roleMapper.toRoleResponseDto(roleUpdated);
    }

    @Override
    @Transactional
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
        log.info("Rol con id {} eliminado exitosamente", roleId);
    }

    private Role findRoleOrThrow(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> {
                    log.debug("No existe rol con id: {}", roleId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with id: " + roleId);
                });
    }

    private Set<Permission> findPermissionsOrThrow(Set<Long> permissionIds) {
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(permissionIds));

        if (permissions.size() != permissionIds.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more permission ids do not exist");
        }

        return permissions;
    }
}
