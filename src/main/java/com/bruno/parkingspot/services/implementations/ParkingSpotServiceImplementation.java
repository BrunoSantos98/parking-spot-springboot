package com.bruno.parkingspot.services.implementations;

import com.bruno.parkingspot.dtos.ParkingSpotDTO;
import com.bruno.parkingspot.models.CarModel;
import com.bruno.parkingspot.models.DependentsModel;
import com.bruno.parkingspot.models.ParkingSpotModel;
import com.bruno.parkingspot.repositories.ParkingSpotRepository;
import com.bruno.parkingspot.services.CarService;
import com.bruno.parkingspot.services.DependentsService;
import com.bruno.parkingspot.services.ParkingSpotService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotServiceImplementation implements ParkingSpotService {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private DependentsService dependentsService;

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public List<ParkingSpotModel> getParkingSpotForBlock(String block){
        return parkingSpotRepository.findByBlock(block);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment,block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

    public ResponseEntity<Object> postMethodValidations(ParkingSpotDTO parkingSpotDTO) {
        if(existsByParkingSpotNumber(parkingSpotDTO.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }else if(existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Parking Spot already registered for this apartment/block!");
        }else{
            return ResponseEntity.ok().body("");
        }
    }

    public ResponseEntity<Object> getValidations(List<ParkingSpotModel> listBlocks, String block, Pageable pageable) {
        if(block == null){
            return ResponseEntity.status(HttpStatus.OK).body(findAll(pageable));
        }else if(!listBlocks.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(listBlocks);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This block not have residents or doesn't exists");
        }
    }

    @Override
    public ResponseEntity<Object> patchMappingRule(UUID id, String info) {
        switch(info.length()){
            case 7:
                CarModel car = carService.getLicensePlateCar(info);
                Optional<ParkingSpotModel> parkingSpotModel = findById(id);
                if(car==null){
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("License Plate not found");
                }else{
                    ParkingSpotModel psModel = parkingSpotModel.get();
                    psModel.setCar(car);
                    parkingSpotRepository.save(psModel);
                    return ResponseEntity.status(HttpStatus.OK).body(psModel);
                }

            case 11:
                DependentsModel dependentsModel = dependentsService.findByCpf(info);
                ParkingSpotModel psModel = findById(id).get();
                if(dependentsModel==null){
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF not found");
                }else{
                    dependentsModel.setParkingSpotModel(psModel);
                    dependentsService.save(dependentsModel);
                    return ResponseEntity.status(HttpStatus.OK).body(dependentsModel);
                }

            default:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Placa ou CPF nao encontrado, " +
                        "por favor corrija os dados e faça uma nova solicitação");
        }

    }
}
