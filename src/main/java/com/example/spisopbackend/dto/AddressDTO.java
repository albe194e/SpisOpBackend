package com.example.spisopbackend.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String id;
    private String streetName;
    private int postalCode;
    private String city;
    private String houseNumber;
    private String countryCode;
    private String country;
}
