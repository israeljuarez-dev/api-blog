package com.israel.api_blog.post.mapper;

import com.israel.api_blog.post.dto.response.CreatePostResponseDto;
import com.israel.api_blog.post.dto.response.PostResponseDto;
import com.israel.api_blog.post.dto.response.UpdatePostResponseDto;
import com.israel.api_blog.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "authorId", source = "author.authorId")
    @Mapping(target = "authorFullName", expression = "java(post.getAuthor().getFirstName() + \" \" + post.getAuthor().getLastName())")
    PostResponseDto toPostResponseDto(Post post);

    @Mapping(target = "authorId", source = "author.authorId")
    CreatePostResponseDto toCreatePostResponseDto(Post post);

    UpdatePostResponseDto toUpdatePostResponseDto(Post post);
}
