package com.example.VKR.service;


import com.example.VKR.errors.NotFoundException;
import com.example.VKR.model.Computer;
import com.example.VKR.model.InstalledSoftware;
import com.example.VKR.model.User;
import com.example.VKR.repository.ComputerRepository;
import com.example.VKR.repository.InstalledSoftwareRepository;
import com.example.VKR.repository.SoftwareRepository;
import com.example.VKR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class InstalledSoftwareService {

    @Autowired
    InstalledSoftwareRepository installedSoftwareRepository;

    @Autowired
    ComputerRepository computerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SoftwareRepository softwareRepository;


    @Transactional
    public boolean installSoft(Long id, Long softId, Long compId, InstalledSoftware installedSoftware, Principal principal) {
        if (installedSoftwareRepository.findByComputerIdAndSoftwareId(id, installedSoftware.getSoftware().getId()) != null) {
            throw new IllegalArgumentException();
        } else {
            //Computer computer = computerRepository.findById(compId).orElseThrow(NotFoundException::new);
            installedSoftware.setWorkStatus(true);
            installedSoftware.setSoftware(softwareRepository.findById(softId).orElseThrow(NotFoundException::new));
            installedSoftware.setInstallationDate(new Date(System.currentTimeMillis()));
            installedSoftware.setUser(userRepository.findByUsername(principal.getName()).orElseThrow(NotFoundException::new));
            installedSoftware.setComputer(computerRepository.findById(compId).orElseThrow(NotFoundException::new));
            try {
                installedSoftwareRepository.save(installedSoftware);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public boolean editInstallSoft(Long softId){
        InstalledSoftware installedSoftware = installedSoftwareRepository.findById(softId).orElseThrow(NotFoundException::new);
        installedSoftware.setLicenseStart(installedSoftware.getLicenseStart());
        installedSoftware.setLicenseEnd(installedSoftware.getLicenseEnd());
        try {
            installedSoftwareRepository.save(installedSoftware);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Transactional
    public boolean delInstallSoft(Long softId){
       InstalledSoftware installedSoftware = installedSoftwareRepository.getById(softId);
        try {
            installedSoftwareRepository.delete(installedSoftware);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public List<InstalledSoftware> getAllInstalledSoftware(){
        return installedSoftwareRepository.findAll();
    }
}
