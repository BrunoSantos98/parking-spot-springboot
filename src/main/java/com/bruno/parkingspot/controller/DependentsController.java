package com.bruno.parkingspot.controller;

import com.bruno.parkingspot.models.DependentsModel;
import com.bruno.parkingspot.services.DependentsService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/dependents")
public class DependentsController {

    @Autowired
    DependentsService dependentsService;

    @PostMapping
    public ResponseEntity<Object> savingDependents(@RequestBody @Valid DependentsModel dependentsModel){
            ResponseEntity<Object> obj = dependentsService.postValidation(dependentsModel);
            return obj;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDependents(@RequestParam(required = false) String cpf){
        return dependentsService.getValidations(cpf);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneDependents(@PathVariable (value="id" ) UUID id){
        Optional<DependentsModel> dependentsModelOptional = dependentsService.findById(id);
        if(dependentsModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dependent not found by Id solicited");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dependentsModelOptional);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteDependents(@PathVariable (value="id" ) UUID id){
        Optional<DependentsModel> dependentsModelOptional = dependentsService.findById(id);
        if(dependentsModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dependent not found by Id solicited");
        }
        dependentsService.delete(dependentsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Car delete successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateDependents(@PathVariable(value="id") UUID id,
                                                    @RequestBody @Valid DependentsModel dependentModel){
        Optional<DependentsModel> dependentsModelOptional = dependentsService.findById(id);
        if (dependentsModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
        var dependentsModelModelDTO = new DependentsModel();
        BeanUtils.copyProperties(dependentsModelOptional,dependentsModelModelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dependentsService.save(dependentModel));
    }
}
