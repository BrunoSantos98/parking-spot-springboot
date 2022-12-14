package com.bruno.parkingspot.services;

import com.bruno.parkingspot.dtos.ParkingSpotDTO;
import com.bruno.parkingspot.models.ParkingSpotModel;
import com.bruno.parkingspot.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment,block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    public List<ParkingSpotModel> getParkingSpotForBlock(String block){
        return parkingSpotRepository.findByBlock(block);
    }

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

    public ResponseEntity<Object> postMethodValidations(ParkingSpotDTO parkingSpotDTO) {
        if(existsByParkingSpotNumber(parkingSpotDTO.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }else if(existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
        }else{
            return ResponseEntity.ok().body("");
        }
    }

    public ResponseEntity<Object> getValidations(List<ParkingSpotModel> listBlocks, String block, Pageable pageable) {
        if(block == null){
            return ResponseEntity.status(HttpStatus.OK).body(findAll(pageable));
        }else if(!listBlocks.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(listBlocks);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This block not have residents");
        }
    }
}
