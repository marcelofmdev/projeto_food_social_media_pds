package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.controller.dto.PostDto;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.repository.PostJpaRepository;
import br.edu.ufrn.foodium.repository.TagJpaRepository;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagJpaRepository tagJpaRepository;

    public List<Post> getPosts() {
        return postJpaRepository.findAll();
    }

    public Post getPost(Long id) {
        return postJpaRepository.findById(id).orElse(null);
    }

    public Post savePost(PostDto post) {
        User userSearched = userJpaRepository.findById(post.user_id).orElse(null);
        Post postToBeSaved = new Post(post.imageUrl, post.content, post.date, userSearched);
        List<Tag> tags = tagJpaRepository.findAllById(post.tags_ids);

        postToBeSaved.tagPost.addAll(tags);
        return postJpaRepository.save(postToBeSaved);
    }

    public void removePost(Long id) {
        postJpaRepository.deleteById(id);
    }
}
