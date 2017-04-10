package com.weblab.repository;

import com.weblab.model.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amowel on 29.03.17.
 */
@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection,Long> {
    UserConnection findByVkId(Long VkId);
}
