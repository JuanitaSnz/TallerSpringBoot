package com.example.demo.repositories;

import com.example.demo.entities.Owner;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository  extends JpaRepository<Owner,Integer> {

}
