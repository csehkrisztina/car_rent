package com.rent.model.entity;

import com.rent.model.dto.RentDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Rent")
public class RentEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long carId;

    @Column(columnDefinition="DATE")
    private Date startDate;

    @Column(columnDefinition="DATE")
    private Date endDate;

    private float price;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public void update(RentDto rent) {
        this.userId = rent.getUserId();
        this.carId = rent.getCarId();
        this.startDate = rent.getStartDate();
        this.endDate = rent.getEndDate();
        this.price = rent.getPrice();
    }

    public RentDto toDto() {
        RentDto rent = new RentDto();
        rent.setUserId(this.userId);
        rent.setCarId(this.carId);
        rent.setStartDate(this.startDate);
        rent.setEndDate(this.endDate);
        rent.setPrice(this.price);
        return rent;
    }
}
