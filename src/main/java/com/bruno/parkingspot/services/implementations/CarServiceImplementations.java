package com.bruno.parkingspot.services.implementations;

import com.bruno.parkingspot.models.CarModel;
import com.bruno.parkingspot.repositories.CarRepository;
import com.bruno.parkingspot.services.CarService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarServiceImplementations implements CarService {

    @Autowired
    CarRepository carRepository;

    @Transactional
    public CarModel save(CarModel carModel) {
        return carRepository.save(carModel);
    }

    public List<CarModel> findAll() {
        return carRepository.findAll();
    }

    public Optional<CarModel> findById(UUID id) {
        return carRepository.findById(id);
    }

    public CarModel getLicensePlateCar(String licensePlate){
        return carRepository.findByLicensePlate(licensePlate);
    }

    @Transactional
    public void delete(CarModel carModel) {
        carRepository.delete(carModel);
    }

    public ResponseEntity<Object> postMethodValidations(CarModel carModel) {
        if(existsByLicensePlateCar(carModel.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }else{
            return ResponseEntity.ok().body("");
        }
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return carRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public ResponseEntity<Object> getValidations(String licensePlate) {
        List<CarModel> listCars = null;
        if(licensePlate == null){
            listCars = findAll();
            return ResponseEntity.status(HttpStatus.OK).body(listCars);
        }else if(!listCars.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(listCars);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("License plate not found, please try again!");
        }
    }
}
