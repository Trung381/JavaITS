package com.example.vuvantrung.Service;

import com.example.vuvantrung.Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    private void fetchUsersFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");

        try {
            if (inputStream != null) {
                users = mapper.readValue(inputStream, typeReference);
            } else {
                throw new IllegalArgumentException("data.json file not found!");
            }
        } catch (Exception e) {
            logger.error("Error loading users", e);
        }
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User save(User user) {
        users.add(user);
        return user;
    }

    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public User update(Long id, User updatedUser) {
        Optional<User> userOptional = findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return user;
        }
        return null;
    }
}
