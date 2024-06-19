package de.confitech.microservice.rest;

import de.confitech.microservice.dto.PostDto;
import de.confitech.microservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public List<PostDto> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public PostDto createPost(@RequestBody PostDto postDTO) {
        return postService.createPost(postDTO);
    }
}
