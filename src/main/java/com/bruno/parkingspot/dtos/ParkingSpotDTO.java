package com.bruno.parkingspot.dtos;

import com.bruno.parkingspot.models.ParkingSpotModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Optional;

public class ParkingSpotDTO {
    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    @Size(max=7)
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;

    public ParkingSpotDTO(ParkingSpotModel psModel) {
        this.apartment = psModel.getApartment();
        this.block = psModel.getBlock();
        this.brandCar = psModel.getBrandCar();
        this.parkingSpotNumber = psModel.getParkingSpotNumber();
        this.colorCar = psModel.getColorCar();
        this.licensePlateCar = psModel.getLicensePlateCar();
        this.modelCar = psModel.getModelCar();
        this.responsibleName = psModel.getResponsibleName();
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
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
}
