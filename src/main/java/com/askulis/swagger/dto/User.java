package com.askulis.swagger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class User {

//    id: 1,
//    first_name: "Maurise",
//    last_name: "Shieldon",
//    email: "mshieldon0@squidoo.com",
//    ip_address: "192.57.232.111",
//    latitude: 34.003135,
//    longitude: -117.7228641

    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String ip_address; // "192.57.232.111",
    private double latitude; // 34.003135,
    private double longitude; //: -117.7228641

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String city;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int dist;
}
