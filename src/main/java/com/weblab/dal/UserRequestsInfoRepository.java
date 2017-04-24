package com.weblab.dal;

import com.weblab.model.UserRequestsInfo;

/**
 * Created by amowel on 02.04.17.
 */

public interface UserRequestsInfoRepository {
    UserRequestsInfo saveUserRequestsInfo(UserRequestsInfo userRequestsInfo);

    UserRequestsInfo findUserRequestsInfo(int vkId);

    void updateUserRequestsInfo(UserRequestsInfo userRequestsInfo);

    boolean isBanned(Long vkId);
}
