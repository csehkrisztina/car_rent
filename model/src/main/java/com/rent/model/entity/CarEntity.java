package com.rent.model.entity;

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
public class CarEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String registNumber; // nr de inmatriculare

    private FuelType fuelType;

    private float price;

    private TransmissionType transmissionType;

    private Brand brand;

    public Long getId() {
        return id;
    }

    public String getRegistNumber() {
        return registNumber;
    }

    public void setRegistNumber(String registNumber) {
        this.registNumber = registNumber;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void update(CarDto car) {
        this.registNumber = car.getRegistNumber();
        this.fuelType = car.getFuelType();
        this.price = car.getPrice();
        this.transmissionType = car.getTransmissionType();
        this.brand = car.getBrand();
    }

    public CarDto toDto() {
        CarDto car = new CarDto();
        car.setRegistNumber(this.registNumber);
        car.setFuelType(this.fuelType);
        car.setPrice(this.price);
        car.setTransmissionType(this.transmissionType);
        car.setBrand(this.brand);
        return car;
    }
}
