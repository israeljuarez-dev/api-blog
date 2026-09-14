package com.israel.api_blog.author.service;

import com.israel.api_blog.author.dto.request.CreateAuthorRequestDto;
import com.israel.api_blog.author.dto.request.UpdateAuthorRequestDto;
import com.israel.api_blog.author.dto.response.AuthorResponseDto;
import com.israel.api_blog.author.dto.response.CreateAuthorResponseDto;
import com.israel.api_blog.author.dto.response.UpdateAuthorResponseDto;
import com.israel.api_blog.author.mapper.AuthorMapper;
import com.israel.api_blog.author.model.Author;
import com.israel.api_blog.author.repository.AuthorRepository;
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
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    @Transactional(readOnly = true)
    public AuthorResponseDto getAuthorById(Long authorId) {
        Author author = findAuthorOrThrow(authorId);
        return authorMapper.toAuthorResponseDto(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponseDto> getAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toAuthorResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public CreateAuthorResponseDto createAuthor(CreateAuthorRequestDto createAuthorRequestDto) {
        Author author = authorRepository.save(authorMapper.toAuthor(createAuthorRequestDto));
        log.info("Autor registrado exitosamente con id {}", author.getAuthorId());
        return authorMapper.toCreateAuthorResponseDto(author);
    }

    @Override
    @Transactional
    public UpdateAuthorResponseDto updateAuthor(Long authorId, UpdateAuthorRequestDto updateAuthorRequestDto) {
        Author author = findAuthorOrThrow(authorId);

        author.setFirstName(updateAuthorRequestDto.firstName());
        author.setLastName(updateAuthorRequestDto.lastName());
        author.setBio(updateAuthorRequestDto.bio());

        Author authorUpdated = authorRepository.save(author);
        log.info("Autor con id {} actualizado exitosamente", authorUpdated.getAuthorId());

        return authorMapper.toUpdateAuthorResponseDto(authorUpdated);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
        log.info("Autor con id {} eliminado exitosamente", authorId);
    }

    private Author findAuthorOrThrow(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    log.debug("No existe autor con id: {}", authorId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found with id: " + authorId);
                });
    }
}
