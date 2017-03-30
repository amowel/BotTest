package com.weblab.service;


import com.weblab.model.UserConnection;
import com.weblab.vk.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;


/**
 * Created by amowel on 28.03.17.
 */
@Service
@Slf4j
public class InstagramService {
    @Autowired
    UserConnectionService userConnectionService;
    public void authorize(UserConnection userConnection)
    {
        userConnectionService.create(userConnection);
    }
    public void post(Message message){
        UserConnection userConnection = userConnectionService.findByVkID((long)message.getUserId());
        log.error(""+(long)message.getUserId());
        Instagram4j instagram = login(userConnection);
        String rootPath = System.getProperty("user.home");
        String imagePath = rootPath + File.separator + "app/tmp/";
        String identity = LocalDateTime.now().toString();
        File file = new File(rootPath+imagePath+identity);
        log.error(message.getMessages().get(0).toString());
        try {
            FileUtils.copyURLToFile(message.getMessages().get(0).getAttachment().get(0), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            instagram.sendRequest(new InstagramUploadPhotoRequest(
                   file,
                    message.getBody()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Instagram4j login(UserConnection userConnection){
        log.error(userConnection.toString());
        Instagram4j instagram = Instagram4j.builder().username(userConnection.getUsername()).password(userConnection.getPassword()).build();
        instagram.setup();
        try {
            instagram.login();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instagram;
    }
    public static void main(String[] args) throws IOException {;
        Instagram4j instagram = Instagram4j.builder().username("henaltestbot").password("lesikpisos").build();
        instagram.setup();
        instagram.login();
        instagram.sendRequest(new InstagramUploadPhotoRequest(
                new File("src/main/resources/static/images/WDF_778052.jpg"),
                "Posted with Instagram4j, how cool is that?"));
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("_darksmoon7"));
        System.out.println("ID for @github is " + userResult.getUser().getPk());
        System.out.println("Number of followers: " + userResult.getUser().getFollower_count());
    }
}
