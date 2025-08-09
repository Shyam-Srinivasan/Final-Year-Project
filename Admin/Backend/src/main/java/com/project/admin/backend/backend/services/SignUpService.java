package com.project.admin.backend.backend.services;

import com.project.admin.backend.backend.models.CollegesModel;
import com.project.admin.backend.backend.repositories.CollegesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private CollegesRepository collegesRepository;

    public CollegesModel createOrganization(CollegesModel collegesModel){
        return collegesRepository.save(collegesModel);
    }

    public boolean checkOrganizationExists(CollegesModel collegesModel){
        return collegesRepository.findByName(collegesModel.getName()) == null;
    }
}
