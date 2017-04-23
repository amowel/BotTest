package com.weblab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    @OneToOne(mappedBy = "instagramConnection")
    @JoinColumn(name = "account_id",insertable = false , updatable = false)
    private Account account;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    public InstagramConnection(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

}
