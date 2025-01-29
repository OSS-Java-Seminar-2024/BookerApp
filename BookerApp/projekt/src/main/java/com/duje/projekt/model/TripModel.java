package com.duje.projekt.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Trip")
public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String tripName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date tripDate;
    String tripTime;
    String tripDescription;
    String tripLocation;
    String tripCapacity;
    Integer tripBooker;


    // Many-to-One relationship with UserModel
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel booker;

    // One-to-Many relationship with ReservationModel
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationModel> reservations;

    public UserModel getBooker() {
        return booker;
    }

    public void setBooker(UserModel booker) {
        this.booker = booker;
        if (!booker.getTrips().contains(this)) {
            booker.getTrips().add(this); // Ensure bidirectional consistency
        }
    }

    public TripModel() {
    }

    public TripModel(Integer id, String tripName, Date tripDate, String tripTime, String tripDescription, String tripLocation, String tripCapacity, Integer tripBooker) {
        this.id = id;
        this.tripName = tripName;
        this.tripDate = tripDate;
        this.tripTime = tripTime;
        this.tripDescription = tripDescription;
        this.tripLocation = tripLocation;
        this.tripCapacity = tripCapacity;
        this.tripBooker = tripBooker;
    }

    @Override
    public String toString() {
        return "TripModel{" +
                "id=" + id +
                ", tripName='" + tripName + '\'' +
                ", tripDate=" + tripDate +
                ", tripTime='" + tripTime + '\'' +
                ", tripDescription='" + tripDescription + '\'' +
                ", tripLocation='" + tripLocation + '\'' +
                ", tripCapacity='" + tripCapacity + '\'' +
                ", tripBooker=" + tripBooker +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public String getTripLocation() {
        return tripLocation;
    }

    public void setTripLocation(String tripLocation) {
        this.tripLocation = tripLocation;
    }

    public String getTripCapacity() {
        return tripCapacity;
    }

    public void setTripCapacity(String tripCapacity) {
        this.tripCapacity = tripCapacity;
    }

    public Integer getTripBooker() {
        return tripBooker;
    }

    public void setTripBooker(Integer tripBooker) {
        this.tripBooker = tripBooker;
    }
}
