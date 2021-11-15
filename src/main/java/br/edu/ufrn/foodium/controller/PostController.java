package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.domain.service.PostService;
import br.edu.ufrn.foodium.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getPost() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        post.setDate(LocalDate.now());
        return postService.savePost(post);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.removePost(id);
    }
}
