package com.rent.model.entity;

import com.rent.model.dto.RentDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Rent")
public class Rent extends Base {

    private float price;
    @ManyToOne
    private Users user;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Location location;
    @Column(columnDefinition="DATE")
    private Date startDate;
    @Column(columnDefinition="DATE")
    private Date endDate;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void update(RentDto rent) {
//        this.userId = rent.getUserId();
//        this.carId = rent.getCarId();
        this.startDate = rent.getStartDate();
        this.endDate = rent.getEndDate();
        this.price = rent.getPrice();
    }

    public RentDto toDto() {
        RentDto rent = new RentDto();
        rent.setUserId(this.user.getId());
        rent.setCarId(this.car.getId());
        rent.setStartDate(this.startDate);
        rent.setEndDate(this.endDate);
        rent.setPrice(this.price);
        return rent;
    }
}
