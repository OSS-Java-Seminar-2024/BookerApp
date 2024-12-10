package com.duje.seminar.Model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Preferences")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private String preference;
}
