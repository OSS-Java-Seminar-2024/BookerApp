package com.duje.seminar.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "Trips")
public class Trip {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "trip_name", nullable = false)
        private String tripName;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        private Date date;

        @Column(nullable = false)
        private String location;

        @Column(nullable = false)
        private Integer capacity;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @OneToMany(mappedBy = "trip")
        private List<Reservation> reservations;
}