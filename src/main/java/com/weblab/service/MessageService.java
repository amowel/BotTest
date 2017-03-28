//package com.weblab.service;
//
//import com.weblab.model.ExampleUserDetails;
//import com.weblab.model.Role;
//import com.weblab.model.User;
//import com.weblab.repository.MessageRepository;
//import com.weblab.vk.model.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.List;
//
///**
// * Created by amowel on 23.03.17.
// */
//public class MessageService {
//    @Autowired
//    MessageRepository messageRepository;
//
//    public Message create(String email, String firstName, String lastName, String password) {
//            return messageRepository.save(new Message());
//    }
//
//    Message findByBody(String body) {
//      return messageRepository.findByBody(body);
//    }
//
//    public Message delete(String email) {
//        return null;
//    }
//
//    public List<Message> findAll() {
//        return messageRepository.findAll();
//    }
//}
