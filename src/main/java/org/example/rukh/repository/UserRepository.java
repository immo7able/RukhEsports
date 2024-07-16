package org.example.rukh.repository;

import org.example.rukh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);


    @Modifying
    @Query("UPDATE User u SET u.role = :role WHERE u.id = :id")
    void changeRoleById(@Param("id") int id, @Param("role") String role);

}
