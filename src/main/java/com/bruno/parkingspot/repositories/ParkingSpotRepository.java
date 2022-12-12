package com.bruno.parkingspot.repositories;

import com.bruno.parkingspot.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);
    @Query(value = "SELECT * FROM TB_PARKING_SPOT WHERE block = ?", nativeQuery = true)
    List<ParkingSpotModel>findByBlock(String name);
}
