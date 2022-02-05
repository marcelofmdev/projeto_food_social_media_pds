package br.edu.ufrn.foodium_app.service;

import br.edu.ufrn.foodium_app.controller.dto.post.CreatePostDto;
import br.edu.ufrn.foodium_app.controller.dto.post.UpdatePostDto;
import br.edu.ufrn.framework.domain.exception.NotFoundException;
import br.edu.ufrn.foodium_app.domain.model.Post;
import br.edu.ufrn.framework.domain.model.Tag;
import br.edu.ufrn.framework.domain.model.User;
import br.edu.ufrn.framework.domain.service.ResourceService;
import br.edu.ufrn.framework.domain.service.TagService;
import br.edu.ufrn.framework.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService extends ResourceService<Post> {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagService tagService;

    public void addLikeIntoPost(Integer postId, Integer userId) {
        Post postSearched = resourceJpaRepository.findById(Long.valueOf(postId)).orElse(null);
        User userSearched = userJpaRepository.findById(Long.valueOf(userId)).orElse(null);
        if( postSearched == null)  {
            throw new NotFoundException("Post não encontrado com o id " + postId);
        }
        if( userSearched == null)  {
            throw new NotFoundException("User não encontrado com o id " + userId);
        }

        Integer newLikes = postSearched.getLikes() + 1;
        postSearched.setLikes(newLikes);
        postSearched.getUsersLikes().add(userSearched);
        resourceJpaRepository.save(postSearched);
    }

    public void deleteLikeIntoPost(Integer postId, Integer userId) {
        Post postSearched = resourceJpaRepository.findById(Long.valueOf(postId)).orElse(null);
        User userSearched = userJpaRepository.findById(Long.valueOf(userId)).orElse(null);
        if( postSearched == null)  {
            throw new NotFoundException("Post não encontrado com o id " + postId);
        }
        if( userSearched == null)  {
            throw new NotFoundException("User não encontrado com o id " + userId);
        }

        Integer newLikes = postSearched.getLikes() - 1;
        postSearched.setLikes(newLikes);
        postSearched.getUsersLikes().remove(userSearched);
        resourceJpaRepository.save(postSearched);
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

        return resourceJpaRepository.save(postToBeSaved);
    }

    @Transactional
    public Post updatePost(UpdatePostDto postDto) {
        User userSearched = userJpaRepository.findById(postDto.getId()).orElse(null);

        if(userSearched == null) {
            throw new NotFoundException("Usuário não encontrado com id " + postDto.getUserId());
        }

        Post postToBeUpdated = resourceJpaRepository.findById(postDto.getId()).orElse(null);

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

        return resourceJpaRepository.save(postToBeUpdated);
    }

}
