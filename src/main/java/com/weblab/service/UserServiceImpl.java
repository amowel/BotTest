package com.weblab.service;

import com.weblab.model.ExampleUserDetails;
import com.weblab.model.Role;
import com.weblab.model.User;
import com.weblab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by amowel on 12.03.17.
 */
@Transactional
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired

    private BCryptPasswordEncoder passwordEncoder;

    public User create(String email, String firstName, String lastName, String password) {
        User user = User
                .getBuilder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .role(Role.ROLE_USER)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        User user = userRepository.findByEmail(s);
        return ExampleUserDetails.getBuilder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .build();
    }

    public User delete(String email) {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
