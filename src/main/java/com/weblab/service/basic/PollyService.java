package com.weblab.service.basic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PollyService {

    private final AmazonPollyClient polly;
    private final Voice voice;

    public PollyService(Region region) {

        polly = new AmazonPollyClient(new ClasspathPropertiesFileCredentialsProvider("AwsPollyCredentials.properties"),
                new ClientConfiguration());
        polly.setRegion(region);
        voice = new Voice();
        voice.setId("Maxim");
    }

    public InputStream synthesize(String text, OutputFormat format) throws IOException {
        SynthesizeSpeechRequest synthReq =
                new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
                        .withOutputFormat(format);
        SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

        return synthRes.getAudioStream();
    }

    public InputStream synthesize(String text, OutputFormat format, String voice) throws IOException {
        SynthesizeSpeechRequest synthReq =
                new SynthesizeSpeechRequest().withText(text).withVoiceId(voice)
                        .withOutputFormat(format);
        SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

        return synthRes.getAudioStream();
    }

    public void play(String sample) throws IOException, JavaLayerException {
        InputStream speechStream = this.synthesize(sample, OutputFormat.Mp3);
        AdvancedPlayer player = new AdvancedPlayer(speechStream,
                javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
        player.play();
    }

    public List<Voice> getAllVoices() {
        DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
        DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
        return describeVoicesResult.getVoices();
    }

    public File createFile(String text, String author) throws IOException {
        String rootPath = System.getProperty("user.home");
        String imagePath = rootPath + File.separator + "/tmp";
        String identity = LocalDateTime.now().toString();
		File file = new File(imagePath
				+author+"|"+identity+".3g") ;
        OutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(this.synthesize(text, OutputFormat.Mp3), outputStream);
        outputStream.close();
        return file;
    }

    public static void main(String[] args) {

    }

}