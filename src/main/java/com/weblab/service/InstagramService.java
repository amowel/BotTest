package com.weblab.service;


import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAttachment;
import com.vk.api.sdk.objects.messages.MessageAttachmentType;
import com.vk.api.sdk.objects.photos.Photo;
import com.weblab.exceptions.BadImageAspectRatio;
import com.weblab.exceptions.ImageNotFoundException;
import com.weblab.exceptions.LoginFailedException;
import com.weblab.exceptions.LogoutFailedException;
import com.weblab.model.UserConnection;
import com.weblab.service.basic.FileService;
import com.weblab.service.dal.UserConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by amowel on 28.03.17.
 */
@Service
@Slf4j
public class InstagramService {
    @Autowired
    private UserConnectionService userConnectionService;
    @Autowired
    private FileService fileService;

    public void authorize(UserConnection userConnection) throws LoginFailedException {

        if (userConnectionService.findByVkId(userConnection.getVkId()) != null) {
            LoginFailedException exception = new LoginFailedException("Entry with this " + userConnection.getVkId() + " vkId already exist");
            exception.setFeedBackMessage("You`re already logged in");
            throw exception;
        }
        login(userConnection);
        userConnectionService.create(userConnection);

    }

    public void post(Message message) throws ImageNotFoundException, LoginFailedException, BadImageAspectRatio {
        File file = null;
        try {
            UserConnection userConnection = userConnectionService.findByVkId((long) message.getUserId());
            if (userConnection == null) {
                LoginFailedException loginFailedException = new LoginFailedException("There is no corresponding pair " +
                        "vkId/instagram credentials in database");
                loginFailedException.setVkMessage(message);
                loginFailedException.setFeedBackMessage("You`re not logged in yet");
                throw loginFailedException;
            }
            Instagram4j instagram = login(userConnection);
            file = File.createTempFile(fileService.generateImageFilename(), "");
            List<Photo> photos = getPhotosFromMessage(message);
            FileUtils.copyURLToFile(getUrlFromHighestQualityPhoto(photos.get(0)), file);
            instagram.sendRequest(new InstagramUploadPhotoRequest(
                    file,
                    message.getBody().replace("post ", "")));
        } catch (IllegalArgumentException e) {
            BadImageAspectRatio exception = new BadImageAspectRatio("Instagram allowed aspect ratio is from 0.8 to 1.91", e);
            exception.setFeedBackMessage("The photo you tried to post has not allowed by instagram proportions");
            throw exception;
        } catch (IOException|IndexOutOfBoundsException e) {
            ImageNotFoundException exception = new ImageNotFoundException("Can`t find photo in received message", e);
            exception.setFeedBackMessage("You need to send photo as an attachment to" +
                    " your message or forwarded messages");
            throw exception;
        }
        finally {
            if (file != null)
                file.delete();
        }
    }

    public void logout(Integer id) throws LogoutFailedException {
        userConnectionService.delete(Long.valueOf(id.toString()));
    }

    private Instagram4j login(UserConnection userConnection) throws LoginFailedException {
        log.info(userConnection.toString());
        try {
            Instagram4j instagram = Instagram4j.builder().username(userConnection.getUsername()).password(userConnection.getPassword()).build();
            instagram.setup();
            instagram.login();
            return instagram;
        } catch (LinkageError | Exception e) {
            LoginFailedException exception = new LoginFailedException("Can`t login in instagram. Likely the cause is " +
                    "wrong credentials, but also can be something much worse",e);
            exception.setFeedBackMessage("Wrong username or password");
            throw exception;
        }

    }

    private List<Photo> getPhotosFromMessage(Message message) {
        List<Photo> list = new ArrayList<>();
        if (message.getAttachments() != null) {
            return message.getAttachments()
                    .stream()
                    .filter(messageAttachment -> messageAttachment.getType() == MessageAttachmentType.PHOTO)
                    .map(MessageAttachment::getPhoto)
                    .collect(Collectors.toList());
        } else if (message.getFwdMessages() != null && !message.getFwdMessages().isEmpty()) {
            for (Message forwardedMessage : message.getFwdMessages())
                list.addAll(getPhotosFromMessage(forwardedMessage));
            return list;
        }
        return Collections.emptyList();
    }

    private URL getUrlFromHighestQualityPhoto(Photo photo) throws ImageNotFoundException {

        try {
            if (photo.getWidth() > 2560)
                return new URL(photo.getPhoto2560());
            else if (photo.getWidth() > 1280)
                return new URL(photo.getPhoto1280());
            else if (photo.getWidth() > 807)
                return new URL(photo.getPhoto807());
            else if (photo.getWidth() > 604)
                return new URL(photo.getPhoto604());
            else if (photo.getWidth() > 130)
                return new URL(photo.getPhoto130());
            else if (photo.getWidth() > 75)
                return new URL(photo.getPhoto75());
            else
                throw new MalformedURLException();

        } catch (MalformedURLException e) {
            throw new ImageNotFoundException("Something wrong with url", e);
        }
    }
}
