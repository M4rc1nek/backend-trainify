package com.trainify.trainifybackend.user.repository;

import com.trainify.trainifybackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByEmail(String email); // metoda do znajdowania użytkownika po nazwie użytkownika lub emailu



    boolean existsByUsername(String username); // metoda do sprawdzania czy istnieje użytkownik o danej nazwie użytkownika
    boolean existsByEmail(String email); // metoda do sprawdzania czy istnieje użytkownik o danym emailu
}
