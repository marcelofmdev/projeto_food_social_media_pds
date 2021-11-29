package br.edu.ufrn.foodium.domain.service;

import br.edu.ufrn.foodium.domain.model.Tag;
import br.edu.ufrn.foodium.repository.TagJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagJpaRepository tagJpaRepository;

    public List<Tag> getTags() {
        return tagJpaRepository.findAll();
    }

    public List<Tag> getAllByIds(List<Long> ids) {
        return tagJpaRepository.findAllById(ids);
    }
}
