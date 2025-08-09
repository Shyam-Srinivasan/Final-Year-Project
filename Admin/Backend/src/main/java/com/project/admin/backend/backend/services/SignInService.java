package com.project.admin.backend.backend.services;


import com.project.admin.backend.backend.models.CollegesModel;
import com.project.admin.backend.backend.repositories.CollegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    @Autowired
    private CollegesRepository collegesRepository;

    public String signIn(String college_name){
        if(validateUser(college_name)){
            return "Welcome to Home Page";
        }
        else{
            return "No details found!";
        }
    }

    public boolean validateUser(String college_name){
        return collegesRepository.findByName(college_name) != null;
    }
}
