package com.example.demo.repositories;

import com.example.demo.entities.IdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdCardRepository extends JpaRepository<IdCard, Integer> {

}
