package com.example.VKR.service;


import com.example.VKR.errors.NotFoundException;
import com.example.VKR.model.Computer;
import com.example.VKR.model.Room;
import com.example.VKR.model.Workplace;
import com.example.VKR.repository.ComputerRepository;
import com.example.VKR.repository.RoomRepository;
import com.example.VKR.repository.WorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkplaceService {

    @Autowired
    WorkplaceRepository workplaceRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ComputerRepository computerRepository;


    @Transactional
    public boolean addWorkplace(String number, Long roomId, Long computerId) {
        Computer computer = null;
        Workplace workplace = new Workplace();
        //Для обязательных
        Room room = roomRepository.findById(roomId).orElseThrow(NotFoundException::new);
        //Для тех, что может быть пустым
        if (computerId != null) {
            computer = computerRepository.findById(computerId).orElseThrow(NotFoundException::new);
        }
        workplace.setNumber(number);
        workplace.setRoom(room);
        workplace.setComputer(computer);
        try {
            workplaceRepository.save(workplace);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean editWorkplace(Long workplaceId, String number, Long computerId) {
        Workplace workplace = workplaceRepository.findById(workplaceId).orElseThrow(NotFoundException::new);
        Computer computer = null;
        if (computerId != null) {
            computer = computerRepository.findById(computerId).orElseThrow(NotFoundException::new);
        }
        if (computer != null && !computer.getWorkplace().getId().equals(workplaceId)) {
            throw new IllegalArgumentException();
        }
        workplace.setNumber(number);
        workplace.setComputer(computer);

        return true;
    }

    @Transactional
    public boolean delWorkplace(Long id) {
        Workplace workplace = workplaceRepository.findById(id).orElseThrow(NotFoundException::new);
        if (workplace.getComputer() != null) {
            workplace.getComputer().setWorkplace(null);
            workplace.getComputer().setUsed(false);
            computerRepository.save(workplace.getComputer());
        }
        workplaceRepository.delete(workplace);

        return true;
    }

    public List<Workplace> getAllWorkplaces() {
        return workplaceRepository.findAll();
    }
}
