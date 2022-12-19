package com.bruno.parkingspot.dtos;

import com.bruno.parkingspot.models.CarModel;
import com.bruno.parkingspot.models.DependentsModel;
import com.bruno.parkingspot.models.ParkingSpotModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class ParkingSpotDTO {
    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;
    private CarModel car;
    private Set<DependentsModel> dependents;

    public ParkingSpotDTO() {
    }

    public ParkingSpotDTO(ParkingSpotModel psModel) {
        this.apartment = psModel.getApartment();
        this.block = psModel.getBlock();
        this.parkingSpotNumber = psModel.getParkingSpotNumber();
        this.responsibleName = psModel.getResponsibleName();
        this.car = psModel.getCar();
        this.dependents = psModel.getDependents();
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public CarModel getCar() {
        return car;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }

    public Set<DependentsModel> getDependents() {
        return dependents;
    }
}
