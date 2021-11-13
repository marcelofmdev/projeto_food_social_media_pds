package br.edu.ufrn.foodium.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsFromUser(Long userId) {
        return postRepository.findPostFromUser(userId);
    }

    public void addNewPost(Post post) {
        Optional<Post> postOptional =
                postRepository.findPostById(post.getId());

        if(postOptional.isPresent()) {
            throw new IllegalStateException("post's id " +
                    post.getId() + " already exists");
        }

        System.out.println("New post to insert " + post);
        postRepository.insertPost(post);
    }

    public void deletePost(Long postId) {
        boolean exists = postRepository.existsById(postId);
        if(!exists) {
            throw new IllegalStateException("post with id " +
                    Long.toString(postId) + " does not exist");
        }
        postRepository.deleteById(postId);
    }
}
