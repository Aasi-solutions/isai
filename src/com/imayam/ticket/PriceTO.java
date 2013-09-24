package com.imayam.ticket;

import java.io.Serializable;
import java.sql.Timestamp;

public class PriceTO implements Serializable {
    private String name;
    private float price;
    private String movie;
    private String venue;
    private String address1;
    private String city;
    private String state;
    private String zip;
    private int scheduleId;
    private Timestamp scheduleDateTime;
    private int ticketCount = 1;
    private String validateRegister;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Timestamp getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(Timestamp scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getValidateRegister() {
        return validateRegister;
    }

    public void setValidateRegister(String validateRegister) {
        this.validateRegister = validateRegister;
    }

}
