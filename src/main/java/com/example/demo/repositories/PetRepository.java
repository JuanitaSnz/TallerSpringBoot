package com.example.demo.repositories;

import com.example.demo.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Integer> {

   public List<Pet> findBySex(String sex);

   @Query(value="select * from pets where name like :beginWith%",nativeQuery=true)
    public List<Pet> namePet(@Param("beginWith") String beginWith);
}
