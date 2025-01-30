package com.duje.projekt.Repository;

import com.duje.projekt.model.ReservationModel;
import com.duje.projekt.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModel,Integer> {
    List<ReservationModel> findByTripBookerAndStatus(UserModel booker, String status);
    List<ReservationModel> findByTripIdAndStatus(Integer tripId, String status);

}
