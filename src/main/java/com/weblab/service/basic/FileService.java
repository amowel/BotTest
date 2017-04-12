package com.weblab.service.basic;

import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by amowel on 04.04.17.
 */
@Service
public class FileService {
    private Random random;

    public FileService() {
        random = new Random();
    }

    public String generateAudioFileName(int id) {
        return String.valueOf(id) + random.nextInt(1000) + ".3g";
    }

    public String generateImageFilename() {
        return new StringBuilder()
                .append(System.getProperty("user.home"))
                .append(File.separator)
                .append("app")
                .append(File.separator)
                .append("tmp")
                .append(File.separator)
                .append(LocalDateTime.now())
                .append(".jpg").toString();

    }
}
