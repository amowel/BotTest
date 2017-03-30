package com.weblab;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.weblab.model.UserConnection;
import com.weblab.service.InstagramService;
import com.weblab.service.UserServiceImpl;
import com.weblab.service.EmailServiceWrapper;
import com.weblab.service.PollyService;
import com.weblab.vk.VkService;
import com.weblab.vk.model.Message;
import javazoom.jl.decoder.JavaLayerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.MessageFormat;
import java.util.Arrays;
@Slf4j
@Controller
@CrossOrigin
public class MainController {

    @Autowired
    EmailServiceWrapper emailServiceWrapper;
    @Autowired
    VkService vk;
    @Autowired
    DataSource dataSource;
    @Autowired
    private UserServiceImpl userDetailService;
    @Autowired
    InstagramService instagramService;
    @Autowired
    private PollyService pollyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() throws IOException, JavaLayerException {

        return "home";
    }

    @RequestMapping(value = "/vk", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> vk(@RequestBody String json) throws IOException, ClientException, ApiException {
        log.error(json);

        Message message = vk.parseMessage(json);
        if(message.getBody().startsWith("/inst auth"))
        {
            String body = message.getBody();
            String[] credentials=body.replace("/inst auth ","").split(" ");
            log.error(Arrays.deepToString(credentials));
            instagramService.authorize(
                   new UserConnection(
                    (long)message.getUserId(),
                    credentials[0],
                    credentials[1]

            ));
            return new ResponseEntity<String>("ok", HttpStatus.OK);
        }
       else if(message.getBody().startsWith("/inst post "))
        {
            String body = message.getBody().replace("/inst post ","");
            message.setBody(body);
            instagramService.post(message);
        }
       else if(message.getBody().length()<500)
                vk.sendAudio(message);
        else {
            message.setBody(MessageFormat.format("Максимальна кількість символів 500. Ви ввели {0}",message.getBody().length()));
            vk.sendMessage(message);
        }
                return new ResponseEntity<String>("ok", HttpStatus.OK);
    }


    @PostMapping("register")
    public String registration(HttpServletRequest request,
                               @RequestParam(value = "username", required = true) String email,
                               @RequestParam(value = "password", required = true) String password) throws UnsupportedEncodingException {

        System.out.println(email);
//        emailServiceWrapper.sendEmail(email);
        userDetailService.create(email, "", "", password);
        return "";
    }

    @RequestMapping(value = "getcode", method = RequestMethod.GET)
    public String status(@RequestParam(value = "code", required = true) String code,
                         ModelMap model) throws IOException {

        return "mainpage";
    }



    @GetMapping("mainpage")
    public String mainpage() {
        return "mainpage";
    }

    @PostMapping("audio**")
    @ResponseBody
    public String audio(@RequestBody String text, Principal principal) throws IOException {
        return pollyService.createFile(text, principal.getName()).getName();

    }


}
