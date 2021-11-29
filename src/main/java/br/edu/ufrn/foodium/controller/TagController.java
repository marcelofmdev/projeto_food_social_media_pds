package br.edu.ufrn.foodium.controller;

import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.domain.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAll() {
        return tagService.getTags();
    }
}
