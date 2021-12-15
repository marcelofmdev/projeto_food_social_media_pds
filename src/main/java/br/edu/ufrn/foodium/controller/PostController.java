package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.controller.dto.post.CreatePostDto;
import br.edu.ufrn.foodium.controller.dto.post.UpdatePostDto;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.service.PostService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping
    public Post createPost(@Valid @RequestBody CreatePostDto post) {
        return postService.savePost(post);
    }

    @PutMapping
    public Post updatePost(@Valid @RequestBody UpdatePostDto post) {
        return postService.updatePost(post);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.removePost(id);
    }
}
