package org.web.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsJpa extends JpaRepository<Persons, Long> {

    Optional<Persons> findByFirstNameAndLastName(String name, String last);

    @Query(value = "SELECT * FROM Persons ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Persons> getRandomPerson();

}