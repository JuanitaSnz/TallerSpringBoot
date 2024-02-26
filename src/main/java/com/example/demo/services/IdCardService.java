package com.example.demo.services;

import com.example.demo.entities.IdCard;
import com.example.demo.entities.Pet;
import com.example.demo.repositories.IdCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdCardService {

    @Autowired
    private IdCardRepository idCardRepository;

    public List<IdCard> findAll(){return idCardRepository.findAll();}

    public IdCard findById(Integer id){
        Optional<IdCard> optional= idCardRepository.findById(id);
        return optional.isPresent() ? optional.get():null;
    }

    public IdCard save (Pet pet, IdCard idCard){
        idCard.setPet(pet);
        return idCardRepository.save(idCard);

    }

    public void delete(IdCard idCard){
       Pet pet= idCard.getPet();
       if(pet !=null){
           pet.setIdCard(null);
       }
       idCardRepository.delete(idCard);
    }

    public IdCard update(IdCard idCard){
        Optional<IdCard> existingCard= idCardRepository.findById(idCard.getId());
        if(existingCard.isPresent()){
            IdCard existing= existingCard.get();

            existing.setExpeditionDate(idCard.getExpeditionDate());

            IdCard updated = idCardRepository.save(existing);
            return  updated;
        }
        return null;
    }

}
