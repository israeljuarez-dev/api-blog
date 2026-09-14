package com.israel.api_blog.user.repository;

import com.israel.api_blog.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByEmailAndUserIdNot(String email, Long userId);
}
