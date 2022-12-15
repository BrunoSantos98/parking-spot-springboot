package com.bruno.parkingspot.services;

import com.bruno.parkingspot.models.DependentsModel;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DependentsService {

    @Transactional
    public DependentsModel save(DependentsModel dependentsModel);
    public List<DependentsModel> findAll();
    public Optional<DependentsModel> findById(UUID id);
    @Transactional
    public void delete(DependentsModel dependentsModel);
    ResponseEntity<Object> getValidations(String cpf);
    DependentsModel findByCpf(String cpf);
    ResponseEntity<Object> postValidation(DependentsModel dependentsModel);
}
