package com.example.VKR.service;

import com.example.VKR.model.Computer;
import com.example.VKR.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComputerService {

    @Autowired
    ComputerRepository computerRepository;

    @Transactional
    public boolean addComputer(String inv) {
        Computer computer = new Computer();
        computer.setINV(inv);
        try {
            computerRepository.save(computer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean editComputer(String inv, Long id) {
        Computer computer = computerRepository.getById(id);
        computer.setINV(inv);
        try {
            computerRepository.save(computer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteComputer(Long id) {
        Computer computer = computerRepository.getById(id);
        try {
            computerRepository.delete(computer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Computer> getAllComputers(){
        return computerRepository.findAll();
    }
}
