package com.example.microServicioSaludo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class MicroServicioSaludoApplication {

    //to use the microservice from postman or a browser, you need to access http://localhost:8080/hello?name=user

    
    @GetMapping("/hello")
    public HelloResponse hello(@RequestParam("name") String name) {
        LocalDateTime actualDateHour = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String actualDateFormatted = actualDateHour.format(formatter);

        HelloResponse response = new HelloResponse();
        response.addKeyValue("message", "Hi, " + name + "! Welcome to the entry micro service!");
        response.addKeyValue("dateConnected :", actualDateFormatted);
        return response;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroServicioSaludoApplication.class, args);
    }
}

class HelloResponse {
    private Map<String, Object> response = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getResponse() {
        return response;
    }

    public void addKeyValue(String key, Object value) {
        response.put(key, value);
    }
}
