package com.project.backend.backend.services;

import com.project.backend.backend.models.CollegesModel;
import com.project.backend.backend.repositories.CollegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private CollegesRepository collegesRepository;

    public CollegesModel createOrganization(CollegesModel collegesModel){
        collegesModel.setId(null);
        return collegesRepository.save(collegesModel);
    }
}
