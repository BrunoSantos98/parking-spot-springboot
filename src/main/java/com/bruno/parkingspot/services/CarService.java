package com.bruno.parkingspot.services;

import com.bruno.parkingspot.models.CarModel;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarService {

    @Transactional
    public CarModel save(CarModel carModel);
    public List<CarModel> findAll();
    public Optional<CarModel> findById(UUID id);
    public CarModel getLicensePlateCar(String licensePlate);
    @Transactional
    public void delete(CarModel carModel);
    public ResponseEntity<Object> postMethodValidations(CarModel carModel);
    public boolean existsByLicensePlateCar(String licensePlateCar);
    public ResponseEntity<Object> getValidations(String licensePlate);
}
