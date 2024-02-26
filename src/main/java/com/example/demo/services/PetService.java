package com.example.demo.services;

import com.example.demo.entities.Owner;
import com.example.demo.entities.Pet;
import com.example.demo.entities.Vaccine;
import com.example.demo.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private VaccineService vaccineService;

    @Autowired

    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public Pet findById(Integer id){
        Optional<Pet> optional=petRepository.findById(id);
        return  optional.isPresent() ? optional.get():null;
    }

    public Pet save(Pet pet, Owner owner){
        pet.setOwner(owner);

        List<Integer>  vaccineIds=pet.getVaccines().stream()
                            .map(Vaccine::getId)
                            .collect(Collectors.toList());

        List<Vaccine> vaccines=vaccineService.getVaccinesById(vaccineIds);

        if (vaccines.size() != vaccineIds.size()) {
            return null;
        }
        pet.setVaccines(vaccines);
        return petRepository.save(pet);

    }

    public void delete(Pet pet){
        if (pet != null) {
            petRepository.delete(pet);
        }

    }

    public Pet update(Pet pet) {
        Optional<Pet> existingPetOptional = petRepository.findById(pet.getId());
        if (existingPetOptional.isPresent()) {
            Pet existingPet = existingPetOptional.get();

            existingPet.setName(pet.getName());
            existingPet.setBirthDay(pet.getBirthDay());
            existingPet.setRace(pet.getRace());
            existingPet.setSex(pet.getSex());
            if (pet.getOwner() != null) {
                existingPet.setOwner(pet.getOwner());
            }

            if(pet.getVaccines() != null){
                existingPet.setVaccines(pet.getVaccines());
            }
            Pet updatedPet = petRepository.save(existingPet);
            return updatedPet;
        }

        return null;
    }

    public List<Pet> findBySex(String sex){
        return petRepository.findBySex(sex);
    }

    public List<Pet> namePet(String beginWith){
        return petRepository.namePet(beginWith);
    }
}
