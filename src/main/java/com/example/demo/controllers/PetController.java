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
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private OwnerService ownerService;

    //EndPoint para obtener todas las mascotas
    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try{
            List<Pet> result= petService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }

    }

    //EndPoint para obtener una mascota a través de su id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id)
    {
        try{
            Pet pet=petService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,pet);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    //EndPoint  para guardar una mascota con su respectivo dueño
    @PostMapping("/savePet/{id}")
    public ResponseEntity<Object> save(@RequestBody Pet pet, @PathVariable Integer id){
        try{
            Owner owner= ownerService.findById(id);
            if(!owner.equals(null)){
                Pet result= petService.save(pet,owner);
                return ResponseHandler.generateResponse("Success", HttpStatus.CREATED,pet);

            }
            return ResponseHandler.generateResponse("Owner not found", HttpStatus.NOT_FOUND,null);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    //Metodo para eliminar una mascota
    @DeleteMapping("/deletePet/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            Pet pet= petService.findById(id);
            if(pet !=null){
                petService.delete(pet);
                return ResponseHandler.generateResponse("Pet successfully deleted!", HttpStatus.ACCEPTED,null);

            }
            return ResponseHandler.generateResponse("Pet not found", HttpStatus.NOT_FOUND,null);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    //EndPoint para actualizar la información de una mascota
    @PutMapping("/updatePet")
    public ResponseEntity<Object> update( @RequestBody Pet pet){

        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,petService.update(pet));
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    //EndPoint para encontrar a las mascotas del mismo sexo
    @GetMapping("/findBySex/{sex}")
    public ResponseEntity<Object> findBySex(@ PathVariable String sex){
        try{
           List<Pet> result = petService.findBySex(sex);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    //EndPoint para encontrar las mascotas cuyo nombre empiece por una letra específica
    @GetMapping("/namePet/{beginWith}")
    public ResponseEntity<Object> findNamePet(@PathVariable String beginWith){
        try{
            List<Pet> result = petService.namePet(beginWith);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
