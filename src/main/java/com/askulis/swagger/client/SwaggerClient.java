package com.askulis.swagger.client;

import com.askulis.swagger.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Component
public class SwaggerClient {

    @Autowired
    private RestOperations restOperations;

    private final String url;

    @Autowired
    public SwaggerClient(@Value("${bpdts.test.app}") final String url) {
        this.url = url;
    }

    public User getUser(final int id) {
        return restOperations.getForObject(url, User.class, id);
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restOperations.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});
        List<User> users = response.getBody();
        return users;
    }
}
