package com.weblab.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by amowel on 29.03.17.
 */
@Entity
@Table(name = "user_connections")
@Getter
@Setter
@Builder
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vk_id", unique = true)
    private Long vkId;
    @Column(name="token")
    private String token;
    @Column(name = "inst_password")
    private String password;
    @Column(name = "inst_username", unique = true)
    private String username;


    public UserConnection(Long id, Long vkId, String token, String password, String username) {
        this.vkId = vkId;
        this.token = token;
        this.password = password;
        this.username = username;
    }
    public UserConnection(){}
}
