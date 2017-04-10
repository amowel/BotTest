package com.weblab.service.dal;

import com.weblab.exceptions.LogoutFailedException;
import com.weblab.model.UserConnection;
import com.weblab.repository.UserConnectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amowel on 29.03.17.
 */
@Slf4j
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

    public UserConnection create(UserConnection userConnection) {
                return repository.save(userConnection);
    }

    public UserConnection findByVkID(Long vkId) {
        return repository.findByVkId(vkId);

    }

    public List<UserConnection> findAll() {
        return repository.findAll();
    }

    public void delete(Long vkId) throws LogoutFailedException {
        log.info("Trying to logout user with ID: {}", vkId);
        if(findByVkID(vkId)==null)
            throw new LogoutFailedException("You are not logged in yet");
        repository.delete(findByVkID(vkId));
    }


}
