package com.project.admin.backend.backend.controllers;

import com.project.admin.backend.backend.models.CollegesModel;
import com.project.admin.backend.backend.services.SignInService;
import com.project.admin.backend.backend.services.SignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    private final SignUpService signUpService;
    private final SignInService signInService;

    public Controller(SignUpService signUpService, SignInService signInService) {
        this.signUpService = signUpService;
        this.signInService = signInService;
    }

    @PostMapping("/signUp")
    ResponseEntity<CollegesModel> signUpPage(@RequestBody CollegesModel collegesModel){
        return new ResponseEntity<>(signUpService.createOrganization(collegesModel), HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    String signInPage(@RequestParam String college_name){
        return signInService.signIn(college_name);
    }

}
