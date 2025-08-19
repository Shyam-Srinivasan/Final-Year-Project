package com.project.admin.backend.backend.services;

import com.project.admin.backend.backend.models.CollegesModel;
import com.project.admin.backend.backend.repositories.CollegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    @Autowired
    private CollegesRepository collegesRepository;
    
    public CollegesModel getCollegeByName(String collegeName){
        return collegesRepository.findByCollegeName(collegeName);
    }

    public String signIn(String collegeName){
        if(validateUser(collegeName)){
            return "Welcome to Home Page";
        }
        else{
            return "No details found!";
        }
    }

    public boolean validateUser(String collegeName){
        return collegesRepository.findByCollegeName(collegeName) != null;
    }
}
