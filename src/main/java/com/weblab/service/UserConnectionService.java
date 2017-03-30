package com.weblab.service;

import com.weblab.model.UserConnection;
import com.weblab.repository.UserConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amowel on 29.03.17.
 */
@Service
public class UserConnectionService {
    @Autowired
    private UserConnectionRepository repository;

    public UserConnectionRepository getRepository() {
        return repository;
    }

    public void setRepository(UserConnectionRepository repository) {
        this.repository = repository;

    }
    public UserConnection create(UserConnection userConnection)
    {
        return repository.save(userConnection);
    }
    public UserConnection findByVkID(Long VkId)
    {

        return repository.findByVkId(VkId);
    }
    public List<UserConnection> findAll()
    {
        return  repository.findAll();
    }
    public UserConnection delete(UserConnection userConnection){
        repository.delete(userConnection);
        return userConnection;
    }



}
