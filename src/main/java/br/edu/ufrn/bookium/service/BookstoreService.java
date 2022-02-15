package br.edu.ufrn.bookium.service;

import br.edu.ufrn.bookium.controller.dto.restaurant.CreateBookstoreDto;
import br.edu.ufrn.bookium.controller.dto.restaurant.updateBookstoreDto;
import br.edu.ufrn.bookium.domain.model.Bookstore;
import br.edu.ufrn.bookium.framework.domain.exception.NotFoundException;
import br.edu.ufrn.bookium.framework.domain.model.Tag;
import br.edu.ufrn.bookium.framework.domain.service.ResourceService;
import br.edu.ufrn.bookium.framework.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookstoreService extends ResourceService<Bookstore> {

    @Autowired
    private TagService tagService;

    @Transactional
    public Bookstore saveBookstore(CreateBookstoreDto bookstoreDto) {
        Bookstore newBookstore = new Bookstore(bookstoreDto.getName(), bookstoreDto.getDescription(), bookstoreDto.getLogo(), 0, false);

        if (bookstoreDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(bookstoreDto.getTagsIds());
            newBookstore.setTags(tags);
        }

        return resourceJpaRepository.save(newBookstore);
    }

    @Transactional
    public Bookstore updateBookstore(updateBookstoreDto bookstoreDto) {
        Bookstore bookstore = resourceJpaRepository.findById(bookstoreDto.getId()).orElse(null);

        if (bookstore == null) {
            throw new NotFoundException("Restaurante não encontrado com id " + bookstoreDto.getId());
        }

        if (bookstoreDto.getName() != null) {
            bookstore.setName(bookstoreDto.getName());
        }
        if (bookstoreDto.getDescription() != null) {
            bookstore.setDescription(bookstoreDto.getDescription());
        }
        if (bookstoreDto.getLogo() != null) {
            bookstore.setLogo(bookstoreDto.getLogo());
        }

        if (bookstoreDto.getTagsIds() != null) {
            List<Tag> tags = tagService.getAllByIds(bookstoreDto.getTagsIds());
            bookstore.setTags(tags);
        }

        return resourceJpaRepository.saveAndFlush(bookstore);
    }

}
