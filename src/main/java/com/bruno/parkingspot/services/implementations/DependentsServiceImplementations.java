package com.bruno.parkingspot.services.implementations;

import com.bruno.parkingspot.models.DependentsModel;
import com.bruno.parkingspot.repositories.DependentsRepository;
import com.bruno.parkingspot.services.DependentsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DependentsServiceImplementations implements DependentsService {

    @Autowired
    DependentsRepository dependentsRepository;

    @Override
    public ResponseEntity<Object> postValidation(DependentsModel dependentsModel) {
         DependentsModel cpfValid = findByCpf(dependentsModel.getCpf());
         if(cpfValid == null){
             return ResponseEntity.status(HttpStatus.CREATED). body(save(dependentsModel));
         }else{
             return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF ja esta cadastrado, " +
                     "por favor utilize outro número");
         }
    }
    @Transactional
    public DependentsModel save(DependentsModel dependentsModel) {
        return dependentsRepository.save(dependentsModel);
    }

    public List<DependentsModel> findAll() {
        return dependentsRepository.findAll();
    }

    public Optional<DependentsModel> findById(UUID id) {
        return dependentsRepository.findById(id);
    }

    @Transactional
    public void delete(DependentsModel dependentsModel) {
        dependentsRepository.delete(dependentsModel);
    }


    public ResponseEntity<Object> getValidations(String cpf) {
        List<DependentsModel> listDependents = new ArrayList<>();
        if(cpf == null){
            listDependents = findAll();
            return ResponseEntity.status(HttpStatus.OK).body(listDependents);
        }else if(cpf != null){
            return ResponseEntity.status(HttpStatus.OK).body(findByCpf(cpf));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF not found, please try again!");
        }
    }

    public DependentsModel findByCpf(String cpf){
        DependentsModel dependentsModel = dependentsRepository.findByCpf(cpf);
        return dependentsModel;
    }
}
