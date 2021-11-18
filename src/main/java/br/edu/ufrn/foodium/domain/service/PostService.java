package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.controller.dto.post.CreatePostDto;
import br.edu.ufrn.foodium.controller.dto.post.UpdatePostDto;
import br.edu.ufrn.foodium.domain.exception.BusinessException;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.repository.PostJpaRepository;
import br.edu.ufrn.foodium.repository.TagJpaRepository;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public Post savePost(CreatePostDto post) {
        User userSearched = userJpaRepository.findById(post.getUserId()).orElse(null);
        if(userSearched == null) {
            throw new BusinessException("Usuário não encontrado com o id " + post.getUserId(), HttpStatus.NOT_FOUND.value());
        }
        Post postToBeSaved = new Post(post.getImageUrl(), post.getContent(), userSearched);
        List<Tag> tags = tagJpaRepository.findAllById(post.getTagsIds());

        postToBeSaved.tags.addAll(tags);
        return postJpaRepository.save(postToBeSaved);
    }

    public void removePost(Long id) {
        postJpaRepository.deleteById(id);
    }

    public Post updatePost(UpdatePostDto post) {

        User userSearched = userJpaRepository.findById(post.getId()).orElse(null);
        if(userSearched == null) {
            throw new IllegalStateException("Erro ao buscar usuário");
        }

        Post postSave = postJpaRepository.findById(post.getId()).orElse(null);
        if(postSave == null) {
            throw new IllegalStateException("Erro ao buscar post");
        }

        List<Tag> tags = tagJpaRepository.findAllById(post.getTagsIds());

        postSave.setImageUrl(post.getImageUrl());
        postSave.setContent(post.getContent());
        postSave.setUser(userSearched);
        postSave.tags = tags;

        return postJpaRepository.save(postSave);
    }
}
