package com.israel.api_blog.author.controller;

import com.israel.api_blog.author.dto.request.CreateAuthorRequestDto;
import com.israel.api_blog.author.dto.request.UpdateAuthorRequestDto;
import com.israel.api_blog.author.dto.response.AuthorResponseDto;
import com.israel.api_blog.author.dto.response.CreateAuthorResponseDto;
import com.israel.api_blog.author.dto.response.UpdateAuthorResponseDto;
import com.israel.api_blog.author.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable("authorId") Long authorId) {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAuthors() {
        return ResponseEntity.ok(authorService.getAuthors());
    }

    @PostMapping
    public ResponseEntity<CreateAuthorResponseDto> createAuthor(@RequestBody @Valid CreateAuthorRequestDto createAuthorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(createAuthorRequestDto));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<UpdateAuthorResponseDto> updateAuthor(
            @PathVariable("authorId") Long authorId,
            @RequestBody @Valid UpdateAuthorRequestDto updateAuthorRequestDto
    ) {
        return ResponseEntity.ok(authorService.updateAuthor(authorId, updateAuthorRequestDto));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }
}
