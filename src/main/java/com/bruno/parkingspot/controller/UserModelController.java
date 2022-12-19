package com.bruno.parkingspot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/User")
public class UserModelController {

}
