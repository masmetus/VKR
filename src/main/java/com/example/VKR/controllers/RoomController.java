package com.example.VKR.controllers;


import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.dto.some.general.DeleteRequest;
import com.example.VKR.dto.some.room.ChangeRoomRequest;
import com.example.VKR.dto.some.room.CreateRoomRequest;
import com.example.VKR.model.Room;
import com.example.VKR.service.RoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping("/newRoom")
    @ApiOperation("Добавить кабиент")
    public ResponseEntity<MessageResponse> newRoom(@Valid @RequestBody CreateRoomRequest createRoomRequest){
        if(roomService.addRoom(createRoomRequest.getNumber()))
            return ResponseEntity.ok(new MessageResponse("Room add successfully!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @PutMapping("/editRoom")
    @ApiOperation("Изменить номер кабиента")
    public ResponseEntity<MessageResponse> editRoom(@Valid @RequestBody ChangeRoomRequest changeRoomRequest){
        if(roomService.editRoomNumber(changeRoomRequest.getNumber(), changeRoomRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Room number has been changed!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @DeleteMapping("/delRoom")
    @ApiOperation("Удалить кабинет")
    public ResponseEntity<MessageResponse> delRoom(@Valid @RequestBody DeleteRequest deleteRequest){
        if(roomService.deleteRoom(deleteRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Room has been deleted!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @GetMapping("/allRoom")
    @ApiOperation("Список кабинетов")
    public ResponseEntity<List<Room>> roomList(){
        return ResponseEntity.ok(roomService.getAllRooms());
    }
}
