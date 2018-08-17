package com.rent.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.CarDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Car")
public class Car extends Base {

    private String registNumber; // nr de inmatriculare
    private String fuelType;
    private float price;
    private String transmissionType;
    private String brand;
    private boolean available;

    public String getRegistNumber() {
        return registNumber;
    }

    public void setRegistNumber(String registNumber) {
        this.registNumber = registNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void update(CarDto car) {
        this.registNumber = car.getRegistNumber();
        this.fuelType = car.getFuelType();
        this.price = car.getPrice();
        this.transmissionType = car.getTransmissionType();
        this.brand = car.getBrand();
        this.available = car.isAvailable();
    }

    public CarDto toDto() {
        CarDto car = new CarDto();
        car.setRegistNumber(this.registNumber);
        car.setFuelType(this.fuelType);
        car.setPrice(this.price);
        car.setTransmissionType(this.transmissionType);
        car.setBrand(this.brand);
        car.setAvailable(this.isAvailable());
        return car;
    }
}
