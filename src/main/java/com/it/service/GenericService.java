package com.it.service;

import java.util.List;

public interface GenericService<T,U> {

    List<T> findAll();

    T findById(U id);

    T save(T entity) throws Exception;

    T update(T entity) throws Exception;

    void delete(T entity);

    void deleteById(U id);

}
