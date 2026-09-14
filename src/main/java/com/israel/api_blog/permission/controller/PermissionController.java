package com.israel.api_blog.permission.controller;

import com.israel.api_blog.permission.dto.request.CreatePermissionRequestDto;
import com.israel.api_blog.permission.dto.request.UpdatePermissionRequestDto;
import com.israel.api_blog.permission.dto.response.PermissionResponseDto;
import com.israel.api_blog.permission.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/{permissionId}")
    public ResponseEntity<PermissionResponseDto> getPermissionById(@PathVariable("permissionId") Long permissionId) {
        return ResponseEntity.ok(permissionService.getPermissionById(permissionId));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponseDto>> getPermissions() {
        return ResponseEntity.ok(permissionService.getPermissions());
    }

    @PostMapping
    public ResponseEntity<PermissionResponseDto> createPermission(@RequestBody @Valid CreatePermissionRequestDto createPermissionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createPermission(createPermissionRequestDto));
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<PermissionResponseDto> updatePermission(
            @PathVariable("permissionId") Long permissionId,
            @RequestBody @Valid UpdatePermissionRequestDto updatePermissionRequestDto
    ) {
        return ResponseEntity.ok(permissionService.updatePermission(permissionId, updatePermissionRequestDto));
    }

    @DeleteMapping("/delete/{permissionId}")
    public ResponseEntity<Void> deletePermission(@PathVariable("permissionId") Long permissionId) {
        permissionService.deletePermission(permissionId);
        return ResponseEntity.noContent().build();
    }
}
