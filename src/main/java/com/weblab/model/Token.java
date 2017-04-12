package com.weblab.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by amowel on 13.04.17.
 */
@Data
public class Token {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("user_id")
    private Long userId;
    @SerializedName("expires_in")
    private Long expiresIn;

}
