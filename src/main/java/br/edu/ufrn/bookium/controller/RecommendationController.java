package br.edu.ufrn.bookium.controller;

import br.edu.ufrn.bookium.domain.model.Book;
import br.edu.ufrn.bookium.service.BookRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/recommendation")
public class RecommendationController {

    @Autowired
    private BookRecommendationService bookRecommendationService;

    @GetMapping("/{userid}")
    public List<Book> getRecommendedPosts(@PathVariable Long userid) {
        return bookRecommendationService.getRecommendedResources(userid);
    }
}
