package models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @OneToMany(mappedBy = "guide")
    private List<Trip> guidedTrips;

    public User() {

    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", guidedTrips=" + guidedTrips +
                '}';
    }

    public User(Long id, String username, String password, String email, String userType, List<Trip> guidedTrips) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.guidedTrips = guidedTrips;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Trip> getGuidedTrips() {
        return guidedTrips;
    }

    public void setGuidedTrips(List<Trip> guidedTrips) {
        this.guidedTrips = guidedTrips;
    }
}