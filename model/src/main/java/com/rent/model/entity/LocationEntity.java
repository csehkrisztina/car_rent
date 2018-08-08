package com.rent.model.entity;

import com.rent.model.dto.LocationDto;

import javax.persistence.*;

@Entity
@Table(name = "Location")
public class LocationEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String country;

    private String county;

    private String city;

    private String zipCode;

    private String street;

    private String number;

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void update(LocationDto location) {
        this.country = location.getCountry();
        this.county = location.getCounty();
        this.zipCode = location.getZipCode();
        this.street = location.getStreet();
        this.number = location.getNumber();
    }

    public LocationDto toDto() {
        LocationDto location = new LocationDto();
        location.setCountry(this.country);
        location.setCounty(this.county);
        location.setZipCode(this.zipCode);
        location.setStreet(this.street);
        location.setNumber(this.number);
        return location;
    }
}
