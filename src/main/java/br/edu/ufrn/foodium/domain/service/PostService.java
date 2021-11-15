package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.repository.PostJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostJpaRepository postJpaRepository;

    public List<Post> getPosts() {
        return postJpaRepository.findAll();
    }

    public Post getPost(Long id) {
        return postJpaRepository.findById(id).orElse(null);
    }

    public Post savePost(Post post) {
        return postJpaRepository.save(post);
    }

    public void removePost(Long id) {
        postJpaRepository.deleteById(id);
    }
}
