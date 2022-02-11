package br.edu.ufrn.foodium.framework.domain.service.interfaces;


import br.edu.ufrn.foodium.framework.domain.model.Resource;

import java.util.List;

public interface IResourceService<T extends Resource> {
    List<T> getAll();
    T getOne(Long id);
    void remove(Long id);
}
