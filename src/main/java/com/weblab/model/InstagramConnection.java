package com.weblab.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by amowel on 20.04.17.
 */
@Entity
@Table(name = "instagram")
@Data
@NoArgsConstructor
public class InstagramConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Account account;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    public InstagramConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
