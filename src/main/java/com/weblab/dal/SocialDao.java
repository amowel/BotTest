package com.weblab.dal;

import java.util.List;

/**
 * Created by amowel on 20.04.17.
 */
public interface SocialDao<T> {
    List<T> findAll();

    T findById(String id);

    T save(T connection);

    void delete(T connection);
}
