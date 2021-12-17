package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.controller.dto.post.CreatePostDto;
import br.edu.ufrn.foodium.controller.dto.post.UpdatePostDto;
import br.edu.ufrn.foodium.domain.exception.NotFoundException;
import br.edu.ufrn.foodium.domain.model.Post;
import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.model.User;
import br.edu.ufrn.foodium.repository.PostJpaRepository;
import br.edu.ufrn.foodium.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagService tagService;

    public List<Post> getPosts() {
        return postJpaRepository.findAll();
    }

    public Post getPost(Long id) {
        Post post = postJpaRepository.findById(id).orElse(null);

        if (post == null) {
            throw new NotFoundException("Publicação não encontrada com id " + id);
        }

        return post;
    }

    public void addLikeIntoPost(Integer postId, Integer userId) {
        Post postSearched = postJpaRepository.findById(Long.valueOf(postId)).orElse(null);
        User userSearched = userJpaRepository.findById(Long.valueOf(userId)).orElse(null);
        if( postSearched == null)  {
            throw new BusinessException("Post não encontrado com o id " + postId, HttpStatus.NOT_FOUND.value());
        }
        if( userSearched == null)  {
            throw new BusinessException("User não encontrado com o id " + userId, HttpStatus.NOT_FOUND.value());
        }

        Integer newLikes = postSearched.getLikes() + 1;
        postSearched.setLikes(newLikes);
        postSearched.getUsersLikes().add(userSearched);
        postJpaRepository.save(postSearched);
    }

    public void deleteLikeIntoPost(Integer postId, Integer userId) {
        Post postSearched = postJpaRepository.findById(Long.valueOf(postId)).orElse(null);
        User userSearched = userJpaRepository.findById(Long.valueOf(userId)).orElse(null);
        if( postSearched == null)  {
            throw new BusinessException("Post não encontrado com o id " + postId, HttpStatus.NOT_FOUND.value());
        }
        if( userSearched == null)  {
            throw new BusinessException("User não encontrado com o id " + userId, HttpStatus.NOT_FOUND.value());
        }

        Integer newLikes = postSearched.getLikes() - 1;
        postSearched.setLikes(newLikes);
        postSearched.getUsersLikes().remove(userSearched);
        postJpaRepository.save(postSearched);
    }

    @Transactional
    public Post savePost(CreatePostDto postDto) {
        User userSearched = userJpaRepository.findById(postDto.getUserId()).orElse(null);

        if(userSearched == null) {
            throw new NotFoundException("Usuário não encontrado com o id " + postDto.getUserId());
        }

        Post postToBeSaved = new Post(postDto.getImageUrl(), postDto.getContent(), userSearched);

        if (postDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(postDto.getTagsIds());
            postToBeSaved.setTags(tags);
        }

        return postJpaRepository.save(postToBeSaved);
    }

    @Transactional
    public Post updatePost(UpdatePostDto postDto) {
        User userSearched = userJpaRepository.findById(postDto.getId()).orElse(null);

        if(userSearched == null) {
            throw new NotFoundException("Usuário não encontrado com id " + postDto.getUserId());
        }

        Post postToBeUpdated = postJpaRepository.findById(postDto.getId()).orElse(null);

        if(postToBeUpdated == null) {
            throw new NotFoundException("Publicação não encontrada com id " + postDto.getId());
        }

        postToBeUpdated.setImageUrl(postDto.getImageUrl());
        postToBeUpdated.setContent(postDto.getContent());
        postToBeUpdated.setUser(userSearched);

        if (postDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(postDto.getTagsIds());
            postToBeUpdated.setTags(tags);
        }

        return postJpaRepository.save(postToBeUpdated);
    }

    @Transactional
    public void removePost(Long id) {
        try {
            postJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Publicação não encontrada com id " + id);
        }
    }
}
