package com.weblab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by amowel on 20.04.17.
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "account")
    @JoinColumn
    private VkConnection vkConnection;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "account")
    @JoinColumn
    private InstagramConnection instagramConnection;
    @Column(name = "created")
    private LocalDate created;

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }


    public static class AccountBuilder {
        private Long id;
        private String username;
        private String password;
        private VkConnection vkConnection;
        private InstagramConnection instagramConnection;
        private LocalDate created;

        AccountBuilder() {
        }

        public AccountBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AccountBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder vkConnection(VkConnection vkConnection) {
            this.vkConnection = vkConnection;
            return this;
        }

        public AccountBuilder instagramConnection(InstagramConnection instagramConnection) {
            this.instagramConnection = instagramConnection;
            return this;
        }

        public AccountBuilder created(LocalDate created) {
            this.created = created;
            return this;
        }

        public Account build() {
            Account account = new Account(id, username, password, vkConnection, instagramConnection, created);
            if (account.getInstagramConnection() != null)
                account.getInstagramConnection().setAccount(account);
            if (account.getVkConnection() != null)
                account.getVkConnection().setAccount(account);
            return account;
        }

        public String toString() {
            return "com.weblab.model.Account.AccountBuilder(id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", vkConnection=" + this.vkConnection + ", instagramConnection=" + this.instagramConnection + ", created=" + this.created + ")";
        }
    }
}
