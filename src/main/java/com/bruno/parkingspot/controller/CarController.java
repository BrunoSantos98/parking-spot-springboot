package com.bruno.parkingspot.controller;


import com.bruno.parkingspot.models.CarModel;
import com.bruno.parkingspot.services.CarService;
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
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping
    public ResponseEntity<Object> savingCars(@RequestBody @Valid CarModel carModel){
        ResponseEntity<Object> validation = carService.postMethodValidations(carModel);
        if(validation.getBody()==""){
            var carModelDto = new CarModel();
            BeanUtils.copyProperties(carModel, carModelDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carModelDto));
        }else{
            return validation;
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllCars(@RequestParam(required = false) String licensePlate){
        CarModel car = carService.getLicensePlateCar(licensePlate);
        return carService.getValidations(licensePlate, car);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable (value="id" ) UUID id){
        Optional<CarModel> carModelOptional = carService.findById(id);
        if(carModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found by Id solicited");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carModelOptional);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable (value="id" ) UUID id){
        Optional<CarModel> carModelOptional = carService.findById(id);
        if(carModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found by Id solicited");
        }
        carService.delete(carModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Car delete successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value="id") UUID id,
                                                    @RequestBody @Valid CarModel carModel){
        Optional<CarModel> carModelOptional = carService.findById(id);
        if (carModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
        var carModelDTO = new CarModel();
        BeanUtils.copyProperties(carModelOptional,carModelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(carService.save(carModelDTO));
    }
}
