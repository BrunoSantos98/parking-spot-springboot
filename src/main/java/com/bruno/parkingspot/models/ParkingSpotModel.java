package com.bruno.parkingspot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false,unique = true,length = 10)
    private String parkingSpotNumber;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false,length = 130)
    private String responsibleName;
    @Column(nullable = false,length = 30)
    private String apartment;
    @Column(nullable = false,length = 30)
    private String block;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CarModel car;
    @OneToMany(mappedBy = "parkingSpotModel", cascade = CascadeType.ALL)
    private Set<DependentsModel> dependents = new HashSet<>();

    public ParkingSpotModel() {
    }

    public ParkingSpotModel(UUID id, String parkingSpotNumber, LocalDateTime registrationDate,
                            String responsibleName, String apartment, String block) {
        this.id = id;
        this.parkingSpotNumber = parkingSpotNumber;
        this.registrationDate = registrationDate;
        this.responsibleName = responsibleName;
        this.apartment = apartment;
        this.block = block;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
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
