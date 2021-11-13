package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts(@RequestParam(required = false) Long userId) {
        if(userId == null) {
            return postService.getPosts();
        }

        return postService.getPostsFromUser(userId);
    }

    @PostMapping
    public void registerNewPost(@RequestBody Post post) {
        postService.addNewPost(post);
    }

    @DeleteMapping(path = "{postId}")
    public void deletePost(@PathVariable("postId") Long id) {
        postService.deletePost(id);
    }

}
