package com.bruno.parkingspot.controller;

import com.bruno.parkingspot.dtos.ParkingSpotDTO;
import com.bruno.parkingspot.models.CarModel;
import com.bruno.parkingspot.models.DependentsModel;
import com.bruno.parkingspot.models.ParkingSpotModel;
import com.bruno.parkingspot.services.CarService;
import com.bruno.parkingspot.services.DependentsService;
import com.bruno.parkingspot.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity<Object> savingParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
        ResponseEntity<Object> validation = parkingSpotService.postMethodValidations(parkingSpotDTO);
        if(validation.getBody()==""){
            var parkingSpotModel = new ParkingSpotModel();
            BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
            parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
        }else{
            return validation;
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllParkingSpots(@PageableDefault(page=0, size=10,sort = "block",
            direction = Sort.Direction.ASC)Pageable pageable, @RequestParam(required = false) String block){
        List<ParkingSpotModel> listBlocks = parkingSpotService.getParkingSpotForBlock(block);
        return parkingSpotService.getValidations(listBlocks,block,pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable (value="id" ) UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(parkingSpotModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("parking spot not found");
        }
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable (value="id" ) UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(parkingSpotModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("parking spot not found");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("parking spot delete successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value="id") UUID id,
                                                    @RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (parkingSpotModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO,parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }

    @PatchMapping("/{id}/add/{info}")
    public ResponseEntity<Object> addCarInParkingSpot(@PathVariable("id") UUID id,
                                                      @PathVariable("info") String licensePlate){
        ResponseEntity<Object> obj = parkingSpotService.patchMappingRule(id,licensePlate);
        return obj;
    }
}
