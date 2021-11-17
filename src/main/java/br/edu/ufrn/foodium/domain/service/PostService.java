package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.controller.dto.PostDto;
import br.edu.ufrn.foodium.controller.dto.PutPostDto;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.repository.PostJpaRepository;
import br.edu.ufrn.foodium.repository.TagJpaRepository;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

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
        if(userSearched == null) {
            throw new IllegalStateException("Erro ao buscar usuário");
        }
        Post postToBeSaved = new Post(post.imageUrl, post.content, post.date, userSearched);
        List<Tag> tags = tagJpaRepository.findAllById(post.tags_ids);

        postToBeSaved.tag_posts.addAll(tags);
        return postJpaRepository.save(postToBeSaved);
    }

    public void removePost(Long id) {
        postJpaRepository.deleteById(id);
    }

    public Post putPost(PutPostDto post) {

        User userSearched = userJpaRepository.findById(post.user_id).orElse(null);
        if(userSearched == null) {
            throw new IllegalStateException("Erro ao buscar usuário");
        }

        Post postSave = postJpaRepository.findById(post.post_id).orElse(null);
        if(postSave == null) {
            throw new IllegalStateException("Erro ao buscar post");
        }

        List<Tag> tags = tagJpaRepository.findAllById(post.tags_ids);

        postSave.setImage_url(post.imageUrl);
        postSave.setContent(post.content);
        postSave.setDate(post.date);
        postSave.setUser(userSearched);
        postSave.tag_posts = tags;

        return postJpaRepository.save(postSave);
    }
}
