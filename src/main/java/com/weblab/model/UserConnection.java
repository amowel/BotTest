package com.weblab.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by amowel on 29.03.17.
 */
@Entity
@Table(name = "user_connections")
@Data
public class UserConnection {
    public UserConnection() {
    }

    public UserConnection(Long vkId, String username, String password) {

        this.vkId = vkId;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vkid",length = 12, nullable = false, unique = true)
    private Long vkId;
    @Column(name = "username",length = 30, nullable = false)
    private String username;
    @Column(name = "password",length = 12, nullable = false)
    private String password;
}
