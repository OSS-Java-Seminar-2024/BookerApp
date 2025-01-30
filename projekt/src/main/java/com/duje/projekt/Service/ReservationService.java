package com.duje.projekt.Service;


import com.duje.projekt.Repository.ReservationRepository;
import com.duje.projekt.model.ReservationModel;
import com.duje.projekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public void saveReservation(ReservationModel reservationModel) {
        reservationRepository.save(reservationModel);
    }


    public List<ReservationModel> findPendingReservationsByBooker(UserModel booker) {
        return reservationRepository.findByTripBookerAndStatus(booker, "Pending");
    }

    public void updateReservationStatus(Integer reservationId, String status) {
        ReservationModel reservation = reservationRepository.findById(reservationId).orElseThrow();
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

    public List<ReservationModel> findConfirmedReservationsByTrip(Integer tripId) {
        return reservationRepository.findByTripIdAndStatus(tripId, "CONFIRMED");
    }
}
