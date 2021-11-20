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
        Post post = postJpaRepository.findById(id).orElse(null);

        if (post == null) {
            throw new BusinessException("Publicação não encontrada com id " + id, HttpStatus.NOT_FOUND.value());
        }

        return post;
    }

    public Post savePost(CreatePostDto postDto) {
        User userSearched = userJpaRepository.findById(postDto.getUserId()).orElse(null);

        if(userSearched == null) {
            throw new BusinessException("Usuário não encontrado com o id " + postDto.getUserId(), HttpStatus.NOT_FOUND.value());
        }

        Post postToBeSaved = new Post(postDto.getImageUrl(), postDto.getContent(), userSearched);

        if (postDto.getTagsIds() != null) {
            List<Tag> tags = tagJpaRepository.findAllById(postDto.getTagsIds());
            postToBeSaved.setTags(tags);
        }

        return postJpaRepository.save(postToBeSaved);
    }

    public void removePost(Long id) {
        postJpaRepository.deleteById(id);
    }

    public Post updatePost(UpdatePostDto postDto) {
        User userSearched = userJpaRepository.findById(postDto.getId()).orElse(null);

        if(userSearched == null) {
            throw new BusinessException("Usuário não encontrado com id " + postDto.getUserId(), HttpStatus.NOT_FOUND.value());
        }

        Post postToBeUpdated = postJpaRepository.findById(postDto.getId()).orElse(null);

        if(postToBeUpdated == null) {
            throw new BusinessException("Publicação não encontrada com id " + postDto.getId(), HttpStatus.NOT_FOUND.value());
        }

        postToBeUpdated.setImageUrl(postDto.getImageUrl());
        postToBeUpdated.setContent(postDto.getContent());
        postToBeUpdated.setUser(userSearched);

        if (postDto.getTagsIds() != null) {
            List<Tag> tags = tagJpaRepository.findAllById(postDto.getTagsIds());
            postToBeUpdated.setTags(tags);
        }

        return postJpaRepository.save(postToBeUpdated);
    }
}
