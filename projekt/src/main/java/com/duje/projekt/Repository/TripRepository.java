package com.duje.projekt.Repository;

import com.duje.projekt.model.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<TripModel,Integer> {
	List<TripModel> findByBookerId(Integer bookerId);
	//List<TripModel> findByBooker(UserModel booker);

}