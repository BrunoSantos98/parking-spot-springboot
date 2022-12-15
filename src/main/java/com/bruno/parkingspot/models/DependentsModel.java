package com.bruno.parkingspot.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="TB_DEPENDENTS")
public class DependentsModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false,length = 15)
    private String firstName;
    @Column(nullable = false,length = 15)
    private String lastName;
    @Column(nullable = false,length = 10)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date birthDate;
    @Column(nullable = false,length = 11,unique = true)
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "parking_id")
    @JsonIgnore
    private ParkingSpotModel parkingSpotModel;

    public DependentsModel() {
    }

    public DependentsModel(UUID id, String firstName, String lastName, Date birthDate, String cpf) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ParkingSpotModel getParkingSpotModel() {
        return parkingSpotModel;
    }

    public void setParkingSpotModel(ParkingSpotModel parkingSpotModel) {
        this.parkingSpotModel = parkingSpotModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DependentsModel that = (DependentsModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
