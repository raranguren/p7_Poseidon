package com.nnk.springboot.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<T> {

    protected final JpaRepository<T, Integer> repository;
    CrudService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public List<T> readAll() {
        return repository.findAll();
    }
    public Optional<T> read(Integer id) {
        return repository.findById(id);
    }
    public T create(T object){
        return repository.save(object);
    }
    public T update(T object){
        return repository.save(object);
    }
    public void delete(Integer id){
        repository.deleteById(id);
    }
}
