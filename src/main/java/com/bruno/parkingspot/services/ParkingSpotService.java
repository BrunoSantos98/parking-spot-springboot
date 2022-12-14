package com.bruno.parkingspot.services;

import com.bruno.parkingspot.dtos.ParkingSpotDTO;
import com.bruno.parkingspot.models.ParkingSpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSpotService {

    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel);
    public boolean existsByApartmentAndBlock(String apartment, String block);
    public Page<ParkingSpotModel> findAll(Pageable pageable);
    public Optional<ParkingSpotModel> findById(UUID id);
    public List<ParkingSpotModel> getParkingSpotForBlock(String block);
    public void delete(ParkingSpotModel parkingSpotModel);
    public ResponseEntity<Object> postMethodValidations(ParkingSpotDTO parkingSpotDTO);
    public ResponseEntity<Object> getValidations(List<ParkingSpotModel> listBlocks, String block, Pageable pageable);
}
