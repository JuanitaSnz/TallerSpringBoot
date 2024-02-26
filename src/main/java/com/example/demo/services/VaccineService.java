package com.example.demo.services;

import com.example.demo.entities.Pet;
import com.example.demo.entities.Vaccine;
import com.example.demo.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    public List<Vaccine> findAll(){return vaccineRepository.findAll();}

    public Vaccine findById(Integer id){
        Optional<Vaccine> optional= vaccineRepository.findById(id);
        return optional.isPresent() ? optional.get() :null;
    }

    public Vaccine save(Vaccine vaccine){
        return vaccineRepository.save(vaccine);
    }

    public List<Vaccine> getVaccinesById(List<Integer> vaccinesId){
        return vaccineRepository.findAllById(vaccinesId);
    }

    public void delete(Vaccine vaccine){
        List<Pet> pets =vaccine.getPets();
        if(pets != null){
            for (Pet pet : pets){
                pet.getVaccines().remove(vaccine);
            }
        }
        vaccineRepository.delete(vaccine);

    }

    public Vaccine update(Vaccine vaccine){
        Optional<Vaccine> optional= vaccineRepository.findById(vaccine.getId());
        if(optional.isPresent()){
            Vaccine existingVaccine= optional.get();

            existingVaccine.setName(vaccine.getName());
            existingVaccine.setAdministrationDate(vaccine.getAdministrationDate());
            existingVaccine.setDescription(vaccine.getDescription());

            Vaccine updated = vaccineRepository.save(existingVaccine);
            return updated;

        }
        return null;
    }
}
