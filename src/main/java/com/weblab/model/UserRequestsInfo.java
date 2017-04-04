package com.weblab.model;

import lombok.Data;

/**
 * Created by amowel on 02.04.17.
 */
@Data
public class UserRequestsInfo {
    public UserRequestsInfo(long vkId,int requestCounter){
        this.vkId = vkId;
        this.requestCounter = requestCounter;
    }
    private Long vkId;
    private int requestCounter;

}
