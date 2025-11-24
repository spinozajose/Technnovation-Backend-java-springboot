package com.technnovation.technnovation.Repository;

import com.technnovation.technnovation.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCorreo(String correo);
}
