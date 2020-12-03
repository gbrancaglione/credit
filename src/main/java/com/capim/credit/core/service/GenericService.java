package com.capim.credit.core.service;
import com.capim.credit.core.model.GenericModel;
import com.capim.credit.core.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<S extends GenericModel> {

    @Autowired
    private GenericRepository<S> repository;

    public List<S> findAll() {
        return repository.findAll();
    }

    public S save(S s) {
        return repository.save(s);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<S> findById(Long id) {
        return repository.findById(id);
    }

}
