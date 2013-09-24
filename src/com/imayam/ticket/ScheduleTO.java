package com.imayam.ticket;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScheduleTO implements Serializable {
    public ScheduleTO() {
    }

    private Timestamp scheduleDateTime;
    private int movieId;
    private String movieName;
    private int venueId;
    private String venueName;
    private int scheduleId;
    private String notes;
    private String imageLink;
    private String promotion;
    private String address1;
    private String city;
    private String state;
    private String zip;
    private int ticketsSold;
    private int capacity;
    private String isThirdPartyLink;
    private String thirdPartyLink;


    public Timestamp getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(Timestamp scheduleDate) {
        this.scheduleDateTime = scheduleDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
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

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getThirdPartyLink() {
        return thirdPartyLink;
    }

    public void setThirdPartyLink(String thirdPartyLink) {
        this.thirdPartyLink = thirdPartyLink;
    }

    public void setIsThirdPartyLink(String isThirdPartyLink) {
        this.isThirdPartyLink = isThirdPartyLink;
    }

    public String getIsThirdPartyLink() {
        return isThirdPartyLink;
    }

}
