package com.example.demo.controllers;

import com.example.demo.entities.IdCard;
import com.example.demo.entities.Pet;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.IdCardService;
import com.example.demo.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idCard")
public class IdCardController {

    @Autowired
    private IdCardService idCardService;

    @Autowired
    private PetService petService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try {
            List<IdCard> result= idCardService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try{
            IdCard idCard=idCardService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,idCard);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PostMapping("/saveIdCard/{id}")
    public ResponseEntity<Object> save(@PathVariable Integer id, @RequestBody IdCard idCard){
        try {
            Pet pet= petService.findById(id);
            if(!pet.equals(null)){
                IdCard result= idCardService.save(pet,idCard);
                return ResponseHandler.generateResponse("Success", HttpStatus.CREATED,result);
            }
             return ResponseHandler.generateResponse("Pet not found", HttpStatus.NOT_FOUND,null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @DeleteMapping("/deleteIdCard/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            IdCard idCard= idCardService.findById(id);
            if(!idCard.equals(null)){
                idCardService.delete(idCard);
                return ResponseHandler.generateResponse("Success", HttpStatus.ACCEPTED,idCard);
            }
            return ResponseHandler.generateResponse("idCard not found", HttpStatus.NOT_FOUND,null);

        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);

        }

    }

    @PutMapping("/updateIdCard")
    public ResponseEntity<Object> update(@RequestBody IdCard idCard){
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,idCardService.update(idCard));

        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }


}
