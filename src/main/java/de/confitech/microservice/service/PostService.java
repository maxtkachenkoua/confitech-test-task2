package de.confitech.microservice.service;

import de.confitech.microservice.db.model.Post;
import de.confitech.microservice.db.repo.PostRepository;
import de.confitech.microservice.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public PostDto createPost(PostDto postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }
}

