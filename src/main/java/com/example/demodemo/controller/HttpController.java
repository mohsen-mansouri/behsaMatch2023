package com.example.demodemo.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

@RestController
public class HttpController {

    @Autowired
    private RestTemplate restTemplate;
    private String username = "a.rahimi";
    private String password = "Pleasefuckinglogin@1010";
    private static final String url = "https://portal.behsacorp.com/rest/confiforms/1.0/get/101613581/TestAPI/7a098331-3a09-4515-ad93-34ab67e6adf2";


    @PostConstruct
    public void init(){
        responseGetter();
    }

    public void responseGetter(){

        ResponseEntity<String> response = restTemplate.exchange
                (url, HttpMethod.GET, new HttpEntity<String>(createHeaders(username, password)), String.class);

        System.out.println(response);
    }


    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

}
