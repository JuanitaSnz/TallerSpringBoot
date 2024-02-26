package com.example.demo.services;

import com.example.demo.entities.Owner;
import com.example.demo.entities.Pet;
import com.example.demo.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> findAll(){
        return ownerRepository.findAll();
    }

    public Owner findById(Integer id){
        Optional<Owner> optional= ownerRepository.findById(id);
        return optional.isPresent() ? optional.get():null;
    }

    public Owner save(Owner owner){
        return ownerRepository.save(owner);
    }

    public void delete(Owner owner){
        ownerRepository.delete(owner);
    }

    public  List<Pet> getPets(Owner owner){
        return owner.getPets();
    }

    public Owner update(Owner owner){
        Optional<Owner> existingOwner=ownerRepository.findById(owner.getId());
        if(existingOwner.isPresent()){
            Owner existing=existingOwner.get();
            existing.setName(owner.getName());
            existing.setBirthDay(owner.getBirthDay());
            existing.setPhone(owner.getPhone());
            existing.setAddress(owner.getAddress());
            Owner updated= ownerRepository.save(existing);
            return updated;
        }
        return null;
    }




}
