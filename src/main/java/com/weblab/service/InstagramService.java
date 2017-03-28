package com.weblab.service;


import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;


import java.io.File;
import java.io.IOException;


/**
 * Created by amowel on 28.03.17.
 */
public class InstagramService {
    public static void main(String[] args) throws IOException {

        Instagram4j instagram = Instagram4j.builder().username("henaltestbot").password("lesikpisos").build();
        instagram.setup();
        instagram.login();
//        instagram.sendRequest(new InstagramUploadPhotoRequest(
//                new File("src/main/resources/static/images/WDF_778052.jpg"),
//                "Posted with Instagram4j, how cool is that?"));
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("_darksmoon7"));
        System.out.println("ID for @github is " + userResult.getUser().getPk());
        System.out.println("Number of followers: " + userResult.getUser().getFollower_count());
    }
}
