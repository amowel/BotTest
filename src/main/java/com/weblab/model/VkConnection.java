package com.weblab.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by amowel on 20.04.17.
 */
@Entity
@Table(name = "vk")
@Data
@NoArgsConstructor
public class VkConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vkid")
    private String vkId;
    @Column(name = "token")
    private String token;
    @OneToOne
    private Account account;

    public VkConnection(String vkId, String token) {
        this.vkId = vkId;
        this.token = token;
    }
}
