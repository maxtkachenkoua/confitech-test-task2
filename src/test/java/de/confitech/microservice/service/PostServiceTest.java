package de.confitech.microservice.service;

import de.confitech.microservice.db.model.Post;
import de.confitech.microservice.db.model.User;
import de.confitech.microservice.db.repo.PostRepository;
import de.confitech.microservice.db.repo.UserRepository;
import de.confitech.microservice.dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@ComponentScan(basePackages = "de.confitech.microservice")
public class PostServiceTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPosts() {
        User user = new User(1L, "testuser", "password", "USER");
        Post post1 = new Post(1L, "Title1", "Content1", user);
        Post post2 = new Post(2L, "Title2", "Content2", user);

        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        List<PostDto> posts = postService.getAllPosts();

        assertEquals(2, posts.size());
        assertEquals("Title1", posts.get(0).getTitle());
        assertEquals("Title2", posts.get(1).getTitle());
    }

    @Test
    public void testGetPostsByUserId() {
        User user = new User(1L, "testuser", "password", "USER");
        Post post1 = new Post(1L, "Title1", "Content1", user);
        Post post2 = new Post(2L, "Title2", "Content2", user);

        when(postRepository.findByUserId(1L)).thenReturn(Arrays.asList(post1, post2));

        List<PostDto> posts = postService.getPostsByUserId(1L);

        assertEquals(2, posts.size());
        assertEquals("Title1", posts.get(0).getTitle());
        assertEquals("Title2", posts.get(1).getTitle());
    }

}
