package com.example.spisopbackend.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private int id;
    private String streetName, city, houseNumber, countryCode, country;
    private int postalCode;

}
