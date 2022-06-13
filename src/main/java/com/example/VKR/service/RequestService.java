package com.example.VKR.service;

import com.example.VKR.errors.NotFoundException;
import com.example.VKR.model.*;
import com.example.VKR.repository.InstalledSoftwareRepository;
import com.example.VKR.repository.RequestRepository;
import com.example.VKR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    InstalledSoftwareRepository installedSoftwareRepository;

    @Autowired
    UserRepository userRepository;



    @Transactional
    public boolean addRequest (String description, Long instSoftId, User user){
        InstalledSoftware installedSoftware = installedSoftwareRepository.findById(instSoftId).orElseThrow(NotFoundException::new);
        Request request = new Request();
        request.setDescriptionOfTheProblem(description);
        request.setInstalledSoftware(installedSoftware);
        request.setComputer(installedSoftware.getComputer());
        request.setRoom(installedSoftware.getComputer().getWorkplace().getRoom());
        request.setApplicationDate(new Date(System.currentTimeMillis()));
        request.setTeacher(userRepository.findByUsername(user.getUsername()).orElseThrow(NotFoundException::new));
        installedSoftware.setWorkStatus(false);
        request.setStatus(EStatus.Open);

        try {
            installedSoftwareRepository.save(installedSoftware);
            requestRepository.save(request);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean appointResponsible(Long id){
        Request request = requestRepository.findById(id).orElseThrow(NotFoundException::new);
        request.setResponsibleEngineer(userRepository.findByRoles(ERole.Engineer).orElseThrow(NotFoundException::new));
        try {
            requestRepository.save(request);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean starWork(Long id){
        Request request = requestRepository.findById(id).orElseThrow(NotFoundException::new);
        request.setStatus(EStatus.InWork);
        try {
            requestRepository.save(request);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean endWork(Long id){
        Request request = requestRepository.findById(id).orElseThrow(NotFoundException::new);
        request.setStatus(EStatus.Awaits);
        try {
            requestRepository.save(request);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean reOpen(Long id){
        Request request = requestRepository.findById(id).orElseThrow(NotFoundException::new);
        request.setStatus(EStatus.Open);
        try {
            requestRepository.save(request);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public boolean closeRequest(Long id){
        Request request = requestRepository.findById(id).orElseThrow(NotFoundException::new);
        request.setStatus(EStatus.Close);
        try {
            requestRepository.save(request);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delRequest(Long id){
        Request request = requestRepository.findById(id).orElseThrow(NotFoundException::new);
        if(request.getStatus()!= EStatus.Close){
            throw new IllegalArgumentException();
        }else {
            requestRepository.delete(request);
        }
        return true;
    }

    public List<Request> getAllRequest(){
        return requestRepository.findAll();
    }
}
