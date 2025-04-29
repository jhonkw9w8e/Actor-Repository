package com.example.repository;

import java.util.List;

public interface Repository<T>  {
    
    List<T> findAll();
    T getByID(Integer id);
    void save(T t);
    void delete(Integer id);

}
