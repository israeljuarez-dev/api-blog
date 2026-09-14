package com.israel.api_blog.author.mapper;

import com.israel.api_blog.author.dto.request.CreateAuthorRequestDto;
import com.israel.api_blog.author.dto.response.AuthorResponseDto;
import com.israel.api_blog.author.dto.response.CreateAuthorResponseDto;
import com.israel.api_blog.author.dto.response.UpdateAuthorResponseDto;
import com.israel.api_blog.author.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponseDto toAuthorResponseDto(Author author);

    CreateAuthorResponseDto toCreateAuthorResponseDto(Author author);

    UpdateAuthorResponseDto toUpdateAuthorResponseDto(Author author);

    Author toAuthor(CreateAuthorRequestDto createAuthorRequestDto);
}
