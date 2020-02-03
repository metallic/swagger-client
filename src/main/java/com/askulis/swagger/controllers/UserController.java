package com.askulis.swagger.controllers;

import com.askulis.swagger.client.SwaggerClient;
import com.askulis.swagger.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final double LONDON_LAT = 51.509865;
    public static final double LONDON_LON = -0.118092;

    public static final String BASE_URL = "/api";

    @Autowired
    SwaggerClient client;

    @GetMapping("/users")
    List<User> getAllUsers() {
        final List<User> allUsers = client.getAllUsers();
        return allUsers;
    }

    @GetMapping("/city/London/dist/{dist}/users")
    List<User> getUsersWithinLondonDist(@PathVariable final int dist) {
        List<User> allUsers = client.getAllUsers();
        allUsers
                .stream()
                .filter(user -> calculateDistanceInMeters(user.getLatitude(), user.getLongitude()) < dist*1609.34)
                .collect(Collectors.toList());
        return allUsers;
    }

    private double calculateDistanceInMeters(double lat1, double long1) {
        double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, LONDON_LAT, LONDON_LON);
        return dist;
    }
}

