package com.example.VKR.service;


import com.example.VKR.model.Room;
import com.example.VKR.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Transactional
    public boolean addRoom(String number) {
        Room room = new Room();
        room.setRoomNumber(number);
        try {
            roomRepository.save(room);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean editRoomNumber(String number, Long id) {
        Room room = roomRepository.getById(id);
        room.setRoomNumber(number);
        try {
            roomRepository.save(room);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteRoom(Long id) {
        Room room = roomRepository.getById(id);
        try {
            roomRepository.delete(room);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
}
