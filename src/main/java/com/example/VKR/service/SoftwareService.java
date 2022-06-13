package com.example.VKR.service;


import com.example.VKR.model.Software;
import com.example.VKR.repository.SoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SoftwareService {

    @Autowired
    SoftwareRepository softwareRepository;

    @Transactional
    public boolean addSoftware(String name) {
        Software software = new Software();
        software.setName(name);

        try {
            softwareRepository.save(software);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteSoftware(Long id) {
        Software software = softwareRepository.getById(id);
        try {
            softwareRepository.delete(software);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean changeSoftName(String name, Long id){
        Software software = softwareRepository.getById(id);
        software.setName(name);
        try {
            softwareRepository.save(software);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Software> getAllSoftware() {
        return softwareRepository.findAll();
    }


}
