package com.duje.projekt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private TripModel trip;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    private String status = "Pending"; // na primjer ovo bi admin(booker) mora minjat ako prihvaca osobu na putovanje: CONFIRMED, PENDING, CANCELLED
    private String notes;  // Special cases (e.g., triplets, dietary requirements)

    public ReservationModel() {}

    public ReservationModel(TripModel trip, UserModel user, String status, String notes) {
        this.trip = trip;
        this.user = user;
        this.status = status;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TripModel getTrip() {
        return trip;
    }

    public void setTrip(TripModel trip) {
        this.trip = trip;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
