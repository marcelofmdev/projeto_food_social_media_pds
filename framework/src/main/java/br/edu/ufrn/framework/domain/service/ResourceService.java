package br.edu.ufrn.framework.domain.service;

import br.edu.ufrn.framework.domain.exception.NotFoundException;
import br.edu.ufrn.framework.domain.model.Resource;
import br.edu.ufrn.framework.domain.service.interfaces.IResourceService;
import br.edu.ufrn.framework.repository.ResourceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class ResourceService<T extends Resource> implements IResourceService<T> {

    @Autowired
    protected ResourceJpaRepository<T> resourceJpaRepository;

    protected String resourceName = "Recurso";

    public List<T> getAll() {
        return resourceJpaRepository.findAll();
    }

    public T getOne(Long id) {
        T resource = resourceJpaRepository.findById(id).orElse(null);

        if (resource == null) {
            throw new NotFoundException(resourceName + " não encontrado com id " + id);
        }

        return resource;
    }

    @Transactional
    public void remove(Long id) {
        try {
            resourceJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException(resourceName + " não encontrado com id " + id);
        }
    }
}
