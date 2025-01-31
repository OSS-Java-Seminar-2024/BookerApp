package com.duje.projekt.Service;

import com.duje.projekt.Repository.TripRepository;
import com.duje.projekt.model.TripModel;
import com.duje.projekt.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserService userService;

    public List<TripModel> getAllAvailableTrips() {
        return tripRepository.findAll();
    }

    @Transactional
    public void saveTrip(TripModel trip) {
        tripRepository.save(trip);
    }

    public void saveTripWithBooker(TripModel trip, String username) {
        UserModel booker = userService.getUserByUsername(username);
        if (booker != null) {
            booker.addTrip(trip); // Maintain bidirectional relationship
            trip.setBooker(booker);
            tripRepository.save(trip); // Save the trip with the associated booker
        } else {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
    }

    public TripModel getTripById(Integer id) {
        return tripRepository.findById(id).orElse(null);
    }
    public List<TripModel> getTripsByUserId(Integer userId) {
        return tripRepository.findByBookerId(userId);
    }

    public List<TripModel> getTripsByBooker(Integer id) {
        return tripRepository.findByBookerId(id);
    }


}