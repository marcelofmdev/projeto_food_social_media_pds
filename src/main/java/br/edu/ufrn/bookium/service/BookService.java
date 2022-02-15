package br.edu.ufrn.bookium.service;

import br.edu.ufrn.bookium.controller.dto.Book.CreateBookDto;
import br.edu.ufrn.bookium.controller.dto.Book.UpdateBookDto;
import br.edu.ufrn.bookium.framework.domain.exception.NotFoundException;
import br.edu.ufrn.bookium.domain.model.Book;
import br.edu.ufrn.bookium.framework.domain.model.Tag;
import br.edu.ufrn.bookium.framework.domain.model.User;
import br.edu.ufrn.bookium.framework.domain.service.ResourceService;
import br.edu.ufrn.bookium.framework.domain.service.TagService;
import br.edu.ufrn.bookium.framework.repository.ResourceJpaRepository;
import br.edu.ufrn.bookium.framework.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService extends ResourceService<Book> {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private ResourceJpaRepository<Book> BookRepository;

    public void addLikeIntoBook(Integer BookId, Integer userId) {
        Book BookSearched = resourceJpaRepository.findById(Long.valueOf(BookId)).orElse(null);
        User userSearched = userJpaRepository.findById(Long.valueOf(userId)).orElse(null);
        if( BookSearched == null)  {
            throw new NotFoundException("Book não encontrado com o id " + BookId);
        }
        if( userSearched == null)  {
            throw new NotFoundException("User não encontrado com o id " + userId);
        }

        Integer newLikes = BookSearched.getLikes() + 1;
        BookSearched.setLikes(newLikes);
        BookSearched.getUsersLikes().add(userSearched);
        resourceJpaRepository.save(BookSearched);
    }

    public void deleteLikeIntoBook(Integer BookId, Integer userId) {
        Book BookSearched = resourceJpaRepository.findById(Long.valueOf(BookId)).orElse(null);
        User userSearched = userJpaRepository.findById(Long.valueOf(userId)).orElse(null);
        if( BookSearched == null)  {
            throw new NotFoundException("Book não encontrado com o id " + BookId);
        }
        if( userSearched == null)  {
            throw new NotFoundException("User não encontrado com o id " + userId);
        }

        Integer newLikes = BookSearched.getLikes() - 1;
        BookSearched.setLikes(newLikes);
        BookSearched.getUsersLikes().remove(userSearched);
        resourceJpaRepository.save(BookSearched);
    }

    @Transactional
    public Book saveBook(CreateBookDto BookDto) {
        User userSearched = userJpaRepository.findById(BookDto.getUserId()).orElse(null);

        if(userSearched == null) {
            throw new NotFoundException("Usuário não encontrado com o id " + BookDto.getUserId());
        }

        Book BookToBeSaved = new Book(BookDto.getImageUrl(), BookDto.getContent(), userSearched);

        if (BookDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(BookDto.getTagsIds());
            BookToBeSaved.setTags(tags);
        }

        return resourceJpaRepository.save(BookToBeSaved);
    }

    @Transactional
    public Book updateBook(UpdateBookDto BookDto) {
        User userSearched = userJpaRepository.findById(BookDto.getId()).orElse(null);

        if(userSearched == null) {
            throw new NotFoundException("Usuário não encontrado com id " + BookDto.getUserId());
        }

        Book BookToBeUpdated = resourceJpaRepository.findById(BookDto.getId()).orElse(null);

        if(BookToBeUpdated == null) {
            throw new NotFoundException("Publicação não encontrada com id " + BookDto.getId());
        }

        BookToBeUpdated.setImageUrl(BookDto.getImageUrl());
        BookToBeUpdated.setContent(BookDto.getContent());
        BookToBeUpdated.setUser(userSearched);

        if (BookDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(BookDto.getTagsIds());
            BookToBeUpdated.setTags(tags);
        }

        return resourceJpaRepository.save(BookToBeUpdated);
    }

}
