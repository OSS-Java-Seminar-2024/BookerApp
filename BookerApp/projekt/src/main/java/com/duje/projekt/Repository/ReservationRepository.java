package com.duje.projekt.Repository;

import com.duje.projekt.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationModel,Integer> {
}
