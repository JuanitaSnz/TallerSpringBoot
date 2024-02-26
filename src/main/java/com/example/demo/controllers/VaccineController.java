package com.example.demo.controllers;

import com.example.demo.entities.Owner;
import com.example.demo.entities.Vaccine;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.OwnerService;
import com.example.demo.services.VaccineService;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {


    @Autowired
    private VaccineService vaccineService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try {
            List<Vaccine> result= vaccineService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try{
            Vaccine vaccine=vaccineService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,vaccine);
        }catch(Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PostMapping("/saveVaccine")
    public ResponseEntity<Object> sva(@RequestBody Vaccine vaccine){
        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,vaccineService.save(vaccine));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            Vaccine vaccine= vaccineService.findById(id);
            if(!vaccine.equals(null)){
                vaccineService.delete(vaccine);
                return ResponseHandler.generateResponse("Success", HttpStatus.ACCEPTED,vaccine);
            }
            return ResponseHandler.generateResponse("Vaccine not found", HttpStatus.NOT_FOUND,null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PutMapping("/updateVaccine")
    public ResponseEntity<Object> update(@RequestBody Vaccine vaccine){
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,vaccineService.update(vaccine));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
