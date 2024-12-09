package models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


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
        @JoinColumn(name = "guide_id")
        private User user;

        @OneToMany(mappedBy = "reservation")
        private List<Reservation> reservation;

        public Trip() {

        }

        @Override
        public String toString() {
                return "Trip{" +
                        "id=" + id +
                        ", tripName='" + tripName + '\'' +
                        ", date=" + date +
                        ", location='" + location + '\'' +
                        ", capacity=" + capacity +
                        ", user=" + user +
                        ", reservation=" + reservation +
                        '}';
        }

        public Trip(Long id, String tripName, Date date, String location, Integer capacity, User user, List<Reservation> reservation) {
                this.id = id;
                this.tripName = tripName;
                this.date = date;
                this.location = location;
                this.capacity = capacity;
                this.user = user;
                this.reservation = reservation;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTripName() {
                return tripName;
        }

        public void setTripName(String tripName) {
                this.tripName = tripName;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public Integer getCapacity() {
                return capacity;
        }

        public void setCapacity(Integer capacity) {
                this.capacity = capacity;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public List<Reservation> getReservation() {
                return reservation;
        }

        public void setReservation(List<Reservation> reservation) {
                this.reservation = reservation;
        }


}