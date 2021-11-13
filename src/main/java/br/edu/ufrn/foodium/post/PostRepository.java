package br.edu.ufrn.foodium.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    private final PostTemporaryDataStorage ptds;

    @Autowired
    public PostRepository(PostTemporaryDataStorage ptds) {
        this.ptds = ptds;
    }

    // Temporary custom methods
    public List<Post> findAll() {
        return ptds.getAllPosts();
    }

    public Optional<Post> findPostById(Long id) {
        return ptds.getPostById(id);
    }

    public boolean existsById(Long id) {
        return ptds.getPostById(id).isPresent();
    }

    public List<Post> findPostFromUser(Long userId) {
        return ptds.getPostsFromUser(userId);
    }

    public void insertPost(Post post) {
        ptds.insertPost(post);
    }

    public void deleteById(Long id) {
        ptds.deletePost(id);
    }
}
