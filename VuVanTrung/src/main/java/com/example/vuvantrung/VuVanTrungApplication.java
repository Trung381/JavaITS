package com.example.vuvantrung;


import com.example.vuvantrung.Model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class VuVanTrungApplication {

    public static void main(String[] args) {
        SpringApplication.run(VuVanTrungApplication.class, args);

        User u = new User(1L, "trung", "trung@" );

    }
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }

}
