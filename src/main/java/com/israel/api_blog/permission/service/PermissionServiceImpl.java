package com.israel.api_blog.permission.service;

import com.israel.api_blog.permission.dto.request.CreatePermissionRequestDto;
import com.israel.api_blog.permission.dto.request.UpdatePermissionRequestDto;
import com.israel.api_blog.permission.dto.response.PermissionResponseDto;
import com.israel.api_blog.permission.mapper.PermissionMapper;
import com.israel.api_blog.permission.model.Permission;
import com.israel.api_blog.permission.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    @Override
    @Transactional(readOnly = true)
    public PermissionResponseDto getPermissionById(Long permissionId) {
        return permissionMapper.toPermissionResponseDto(findPermissionOrThrow(permissionId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponseDto> getPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PermissionResponseDto createPermission(CreatePermissionRequestDto createPermissionRequestDto) {
        Permission permission = permissionRepository.save(permissionMapper.toPermission(createPermissionRequestDto));
        log.info("Permiso registrado exitosamente con id {}", permission.getPermissionId());
        return permissionMapper.toPermissionResponseDto(permission);
    }

    @Override
    @Transactional
    public PermissionResponseDto updatePermission(Long permissionId, UpdatePermissionRequestDto updatePermissionRequestDto) {
        Permission permission = findPermissionOrThrow(permissionId);
        permission.setName(updatePermissionRequestDto.name());

        Permission permissionUpdated = permissionRepository.save(permission);
        log.info("Permiso con id {} actualizado exitosamente", permissionUpdated.getPermissionId());

        return permissionMapper.toPermissionResponseDto(permissionUpdated);
    }

    @Override
    @Transactional
    public void deletePermission(Long permissionId) {
        permissionRepository.deleteById(permissionId);
        log.info("Permiso con id {} eliminado exitosamente", permissionId);
    }

    private Permission findPermissionOrThrow(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> {
                    log.debug("No existe permiso con id: {}", permissionId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found with id: " + permissionId);
                });
    }
}
