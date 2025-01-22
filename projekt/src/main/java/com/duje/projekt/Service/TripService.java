package com.duje.projekt.Service;

import com.duje.projekt.Repository.TripRepository;
import com.duje.projekt.model.TripModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<TripModel> getAllAvailableTrips() {
        return tripRepository.findAll();
    }
}