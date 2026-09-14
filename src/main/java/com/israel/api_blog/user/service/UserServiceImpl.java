package com.israel.api_blog.user.service;

import com.israel.api_blog.role.model.Role;
import com.israel.api_blog.role.repository.RoleRepository;
import com.israel.api_blog.user.dto.request.RegisterUserRequestDto;
import com.israel.api_blog.user.dto.request.UpdateUserRequestDto;
import com.israel.api_blog.user.dto.response.UserResponseDto;
import com.israel.api_blog.user.mapper.UserMapper;
import com.israel.api_blog.user.model.UserEntity;
import com.israel.api_blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long userId) {
        return userMapper.toUserResponseDto(findUserOrThrow(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDto register(RegisterUserRequestDto registerUserRequestDto) {
        validateUniqueUsername(registerUserRequestDto.username());
        validateUniqueEmail(registerUserRequestDto.email());

        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default role USER not found"));

        UserEntity user = userMapper.toEntity(registerUserRequestDto);

        user.setPassword(passwordEncoder.encode(registerUserRequestDto.password()));
        user.setRoles(Set.of(defaultRole));

        Instant now = Instant.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        UserEntity savedUser = userRepository.save(user);

        log.info("Usuario creado exitosamente con rol {}", savedUser.getRoles());

        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDto   updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto) {
        UserEntity user = findUserOrThrow(userId);

        validateEmailAvailableForUpdate(userId, updateUserRequestDto.email());

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(updateUserRequestDto.roleIds()));

        if (roles.size() != updateUserRequestDto.roleIds().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more role ids do not exist");
        }

        user.setEmail(updateUserRequestDto.email());
        user.setEnabled(updateUserRequestDto.enabled());
        user.setRoles(roles);

        UserEntity userUpdated = userRepository.save(user);
        log.info("Usuario con id {} actualizado exitosamente", userUpdated.getUserId());

        return userMapper.toUserResponseDto(userUpdated);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.info("Usuario con id {} eliminado exitosamente", userId);
    }

    private UserEntity findUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.debug("No existe usuario con id: {}", userId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId);
                });
    }

    private void validateUniqueUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already in use: " + username);
        }
    }

    private void validateUniqueEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use: " + email);
        }
    }
    private void validateEmailAvailableForUpdate(Long userId, String email) {
        if (userRepository.existsByEmailAndUserIdNot(email, userId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use: " + email);
        }
    }
}