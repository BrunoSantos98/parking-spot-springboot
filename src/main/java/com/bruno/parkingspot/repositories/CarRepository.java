package com.bruno.parkingspot.repositories;

import com.bruno.parkingspot.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarModel, UUID> {

    @Query(value = "SELECT * FROM TB_CAR WHERE licensePlateCar = ?", nativeQuery = true)
    List<CarModel> findByLicensePlate(String name);

    boolean existsByLicensePlateCar(String licensePlateCar);
}
