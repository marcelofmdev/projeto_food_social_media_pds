package br.edu.ufrn.bookium.controller;

import br.edu.ufrn.bookium.controller.dto.restaurant.CreateBookstoreDto;
import br.edu.ufrn.bookium.controller.dto.restaurant.updateBookstoreDto;
import br.edu.ufrn.bookium.domain.model.Bookstore;
import br.edu.ufrn.bookium.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/bookstore")
public class BookstoreController {

    @Autowired
    private BookstoreService bookstoreService;

    @GetMapping
    public List<Bookstore> getBookstores() {
        return bookstoreService.getAll();
    }

    @GetMapping("/{id}")
    public Bookstore getBookstore(@PathVariable Long id) {
        return bookstoreService.getOne(id);
    }

    @PostMapping
    public Bookstore createBookstore(@Valid @RequestBody CreateBookstoreDto restaurant) {
        return bookstoreService.saveBookstore(restaurant);
    }

    @PutMapping
    public Bookstore updateBookstore(@Valid @RequestBody updateBookstoreDto restaurant) {
        return bookstoreService.updateBookstore(restaurant);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBookstore(@PathVariable Long id) {
        bookstoreService.remove(id);
    }
}
