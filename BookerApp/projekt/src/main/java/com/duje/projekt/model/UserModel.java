package com.duje.projekt.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
    String role;


    // One-to-Many relationship with TripModel
    @OneToMany(mappedBy = "booker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripModel> trips;

    // One-to-Many relationship with ReservationModel
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationModel> reservations;


    public void addTrip(TripModel trip) {
        trips.add(trip);
        trip.setBooker(this); // Ensure bidirectional consistency
    }

    public List<TripModel> getTrips() {
        return trips;
    }

    public void setTrips(List<TripModel> trips) {
        this.trips = trips;
    }


    @Override
    public String toString() {
        return "UserModel{" +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(username, userModel.username) && Objects.equals(password, userModel.password) && Objects.equals(firstName, userModel.firstName) && Objects.equals(lastName, userModel.lastName) && Objects.equals(email, userModel.email) && Objects.equals(phone, userModel.phone) && Objects.equals(role, userModel.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, phone, role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
//SUPERUSER INSERTION
//INSERT INTO users (username, password, first_name, last_name, email, phone, role)
//VALUES ('superuser', '$2a$10$hbSAMBPPsyZFvcAGikzb8Ogp7WSkOikgDx3nCVUZP5eiCdXbecUBK',
// 'Super', 'User', 'superuser@example.com', '1234567890', 'SUPERUSER');
