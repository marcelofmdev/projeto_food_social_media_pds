package br.edu.ufrn.foodium.framework.controller;

import br.edu.ufrn.foodium.framework.domain.model.Tag;
import br.edu.ufrn.foodium.framework.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
