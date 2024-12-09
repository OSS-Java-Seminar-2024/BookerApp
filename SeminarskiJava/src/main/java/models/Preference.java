package models;
import jakarta.persistence.*;



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

    public Preference() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Preference(Long id, Reservation reservation, String preference) {
        this.id = id;
        this.reservation = reservation;
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", preference='" + preference + '\'' +
                '}';
    }

}
