package com.duje.projekt.Repository;

import com.duje.projekt.model.UserModel;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsernameAndPassword(String username, String password);

    Optional<UserModel> findFirstByUsername(String username);

    List<UserModel> findByRole(String role);
    
    
}
