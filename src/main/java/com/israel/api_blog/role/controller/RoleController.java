package com.israel.api_blog.role.controller;

import com.israel.api_blog.role.dto.request.CreateRoleRequestDto;
import com.israel.api_blog.role.dto.request.UpdateRoleRequestDto;
import com.israel.api_blog.role.dto.response.RoleResponseDto;
import com.israel.api_blog.role.service.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> getRoleById(@Min(1) @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody @Valid CreateRoleRequestDto createRoleRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(createRoleRequestDto));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> updateRole(
            @Min(1) @PathVariable("roleId") Long roleId,
            @RequestBody @Valid UpdateRoleRequestDto updateRoleRequestDto
    ) {
        return ResponseEntity.ok(roleService.updateRole(roleId, updateRoleRequestDto));
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<Void> deleteRole(@Min(1) @PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }
}
