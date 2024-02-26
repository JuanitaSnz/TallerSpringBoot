package com.example.demo.controllers;

import com.example.demo.entities.Owner;
import com.example.demo.entities.Pet;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.OwnerService;
import com.example.demo.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    //EndPoint para encontrar todos los dueños
    @GetMapping()
    public ResponseEntity<Object> findAll() {
        try {
            List<Owner> result = ownerService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }
    //Endpoint para añadir un nuevo dueño
    @PostMapping("/saveOwner")
    public ResponseEntity<Object> save(@RequestBody Owner owner){
        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.CREATED,ownerService.save(owner));
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try{
            Owner owner=ownerService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,owner);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    //Endpoint para encontrar las mascotas de un dueño, por su id
    @GetMapping("/getOwnerPet/{id}")
    public ResponseEntity<Object> getOwners(@PathVariable Integer id){
        try{
            Owner owner= ownerService.findById(id);
            if(!owner.equals(null)){
               List<Pet> result= ownerService.getPets(owner);
                return ResponseHandler.generateResponse("Success", HttpStatus.CREATED,result);

            }
            return ResponseHandler.generateResponse("Owner not found", HttpStatus.NOT_FOUND,null);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    //Endpoint para eliminar un dueño a través de su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            Owner owner= ownerService.findById(id);
            if(!owner.equals(null)){
                ownerService.delete(owner);
                return ResponseHandler.generateResponse("Owner successfully deleted!", HttpStatus.ACCEPTED,null);

            }
            return ResponseHandler.generateResponse("Owner not found", HttpStatus.NOT_FOUND,null);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PutMapping("/updateOwner")
    public ResponseEntity<Object> update(@RequestBody Owner owner){
        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,ownerService.update(owner));
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

}
