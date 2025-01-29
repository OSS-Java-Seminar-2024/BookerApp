package com.duje.projekt.Service;


import com.duje.projekt.Repository.ReservationRepository;
import com.duje.projekt.model.ReservationModel;
import com.duje.projekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public void saveReservation(ReservationModel reservationModel) {
        reservationRepository.save(reservationModel);
    }
}
