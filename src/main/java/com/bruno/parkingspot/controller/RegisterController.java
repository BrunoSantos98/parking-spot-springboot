package com.bruno.parkingspot.controller;

import com.bruno.parkingspot.models.RoleModel;
import com.bruno.parkingspot.models.UserModel;
import com.bruno.parkingspot.repositories.RoleRepository;
import com.bruno.parkingspot.services.implementations.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserDetailsServiceImplementation userImp;
    @Autowired
    private RoleRepository roleRepo;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody UserModel user){
        ResponseEntity<Object> response = ResponseEntity.ok("");
        try{
            String name = userImp.registerNewUser(user);
            response =  ResponseEntity.ok("Seu usuario de nome: " + name + ", foi criado com sucesso! ");
        }catch(IllegalArgumentException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo senha não " +
                    "pode ser nulo ou vazio, por favor preencha o campo senha");
        }catch(DataIntegrityViolationException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo Usuario ja existe, por favor " +
                    "sigite outro nome de Usuario");
        }finally {
            return response;
        }
    }
}
