package com.project.backend.backend.services;

import com.project.backend.backend.models.CollegesModel;
import com.project.backend.backend.repositories.CollegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInRepository {
    @Autowired
    private CollegesRepository collegesRepository;

    private CollegesModel signIn(CollegesModel collegesModel){
        return collegesModel;
    }
}
