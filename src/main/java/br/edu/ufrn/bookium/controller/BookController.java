package br.edu.ufrn.bookium.controller;

import br.edu.ufrn.bookium.controller.dto.Book.CreateBookDto;
import br.edu.ufrn.bookium.controller.dto.Book.UpdateBookDto;
import br.edu.ufrn.bookium.domain.model.Book;
import br.edu.ufrn.bookium.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/Book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getOne(id);
    }

    @PostMapping
    public Book createBook(@Valid @RequestBody CreateBookDto Book) {
        return bookService.saveBook(Book);
    }

    @PutMapping
    public Book updateBook(@Valid @RequestBody UpdateBookDto Book) {
        return bookService.updateBook(Book);
    }

    @PostMapping("/addLike/{BookId}/{userId}")
    public void addLikeIntoBook(@PathVariable Integer BookId, @PathVariable Integer userId) {
        bookService.addLikeIntoBook(BookId, userId);
    }

    @PostMapping("/deleteLike/{BookId}/{userId}")
    public void deleteLikeIntoBook(@PathVariable Integer BookId, @PathVariable Integer userId) {
        bookService.deleteLikeIntoBook(BookId, userId);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.remove(id);
    }
}
