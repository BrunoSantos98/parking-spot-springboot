package com.bruno.parkingspot.repositories;

import com.bruno.parkingspot.models.CarModel;
import com.bruno.parkingspot.models.DependentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DependentsRepository extends JpaRepository<DependentsModel, UUID> {

    @Query(value = "SELECT * FROM TB_DEPENDENTS WHERE cpf = ?", nativeQuery = true)
    DependentsModel findByCpf(String cpf);
}
