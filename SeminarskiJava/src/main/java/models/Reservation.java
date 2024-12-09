package models;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "number_of_people", nullable = false)
    private Integer numberOfPeople;

    @OneToMany(mappedBy = "preference")
    private List<Preference> preferences;


    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", trip=" + trip +
                ", numberOfPeople=" + numberOfPeople +
                ", preferences=" + preferences +
                '}';
    }

    public Reservation(Long id, User user, Trip trip, Integer numberOfPeople, List<Preference> preferences) {
        this.id = id;
        this.user = user;
        this.trip = trip;
        this.numberOfPeople = numberOfPeople;
        this.preferences = preferences;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }

}