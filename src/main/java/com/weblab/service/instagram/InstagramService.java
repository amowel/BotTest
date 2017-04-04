package com.weblab.service.instagram;


import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAttachment;
import com.vk.api.sdk.objects.messages.MessageAttachmentType;
import com.vk.api.sdk.objects.photos.Photo;
import com.weblab.model.UserConnection;

import com.weblab.service.basic.FileService;
import com.weblab.service.dal.UserConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void authorize(UserConnection userConnection)
    {
        userConnectionService.create(userConnection);
    }
    public void post(Message message){
        UserConnection userConnection = userConnectionService.findByVkID((long)message.getUserId());
        Instagram4j instagram = login(userConnection);
        File file = new File(fileService.generateImageFilename());
        List<Photo> photos = getPhotosFromMessage(message);
        try {
            log.error(photos.get(0).getPhoto1280());
                       FileUtils.copyURLToFile(getUrlFromHighestQualityPhoto(photos.get(0)), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            instagram.sendRequest(new InstagramUploadPhotoRequest(
                   file,
                    message.getBody().replace("/inst post ","")));
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
    public List<Photo> getPhotosFromMessage(Message message){
        List<Photo> list = new ArrayList<>();
        if(message.getAttachments()!=null)
        {
//
       return      message.getAttachments()
                    .stream()
                    .filter(messageAttachment -> messageAttachment.getType()== MessageAttachmentType.PHOTO)
                    .map(MessageAttachment::getPhoto)
                    .collect(Collectors.toList());
        }
        else if(message.getFwdMessages()!=null&&!message.getFwdMessages().isEmpty()){
            for(Message forwardedMessage:message.getFwdMessages())
                list.addAll(getPhotosFromMessage(forwardedMessage));
            return list;
        }
        return null;
    }
    public URL getUrlFromHighestQualityPhoto(Photo photo)
    {

            try {
                if(photo.getWidth()>2560)
                    return new URL(photo.getPhoto2560());
                else if(photo.getWidth()>1280)
                    return new URL(photo.getPhoto1280());
                else if(photo.getWidth()>807)
                    return new URL(photo.getPhoto807());
                else if(photo.getWidth()>604)
                    return new URL(photo.getPhoto604());
                else if(photo.getWidth()>130)
                    return new URL(photo.getPhoto130());
                else if(photo.getWidth()>75)
                    return new URL(photo.getPhoto75());
                else
                    throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    public static void main(String[] args) {
        String rootPath = System.getProperty("user.home");
        String imagePath = rootPath + File.separator + "app/tmp/";
        String identity = LocalDateTime.now().toString();
        File file = new File(imagePath+"img");
        try {
            FileUtils.copyURLToFile(new URL("https://pp.userapi.com/c837238/v837238727/30e53/FbNzbI0bgE.jpg"),file);
            BufferedImage image = ImageIO.read(file);
            System.out.println(image.getWidth()*1.0/image.getHeight());
            System.out.println(image.getWidth());
            System.out.println(image.getHeight());
            BufferedImage newImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT,image.getWidth(),image.getHeight());
            System.out.println(newImage.getWidth());
            System.out.println(newImage.getHeight());
            ImageIO.write(newImage,"jpg",file);
            Instagram4j instagram = Instagram4j.builder().username("henaltestbot").password("lesikpisos").build();
            instagram.setup();
            instagram.login();
            instagram.sendRequest(new InstagramUploadPhotoRequest(
                    file,"dwdewdwe"
                    ));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
