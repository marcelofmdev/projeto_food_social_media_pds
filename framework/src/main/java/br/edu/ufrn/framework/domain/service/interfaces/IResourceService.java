package br.edu.ufrn.framework.domain.service.interfaces;

import br.edu.ufrn.framework.domain.model.Resource;
import br.edu.ufrn.framework.domain.model.User;

import java.util.List;

public interface IResourceService<T extends Resource> {
    List<T> getAll();
    T getOne(Long id);
    void remove(Long id);
}
