package com.weblab.service.dal;

import java.util.List;

/**
 * Created by amowel on 20.04.17.
 */
public interface SocialDao<T> {
    public List<T> findAll();
    public T findById(String id);
    public T save(T connection);
    public void delete(T connection);
}
