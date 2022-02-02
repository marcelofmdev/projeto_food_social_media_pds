package br.edu.ufrn.framework.domain.service;

import br.edu.ufrn.framework.domain.model.Tag;
import br.edu.ufrn.framework.repository.TagJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
