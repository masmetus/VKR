package com.example.VKR.controllers;

import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.dto.some.computer.ChangeComputerRequest;
import com.example.VKR.dto.some.computer.ComputerResponse;
import com.example.VKR.dto.some.computer.CreateComputerRequest;
import com.example.VKR.dto.some.general.DeleteRequest;
import com.example.VKR.dto.some.room.ChangeRoomRequest;
import com.example.VKR.dto.some.room.CreateRoomRequest;
import com.example.VKR.model.Computer;
import com.example.VKR.service.ComputerService;
import com.example.VKR.service.RoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/computer")
public class ComputerController {

    @Autowired
    ComputerService computerService;

    @PostMapping("/newComputer")
    @ApiOperation("Добавить компьютер")
    public ResponseEntity<MessageResponse> newComputer(@Valid @RequestBody CreateComputerRequest createComputerRequest){
        if(computerService.addComputer(createComputerRequest.getInv()))
            return ResponseEntity.ok(new MessageResponse("Computer add successfully!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @PutMapping("/editComputer")
    @ApiOperation("Изменить инвентарный номер")
    public ResponseEntity<MessageResponse> editComputer(@Valid @RequestBody ChangeComputerRequest changeComputerRequest){
        if (computerService.editComputer(changeComputerRequest.getInv(), changeComputerRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Computer INV has been changed"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    //Продумать
    //По логике, они должны не удаляться, а у них убирается рабочее место, а сами они, я хз, имеют статус "Списан"
    /*
    @PutMapping("/delComputer")
    @ApiOperation("Списать компьютер")
    public ResponseEntity<MessageResponse> delComputer(@Valid @RequestBody DeleteRequest deleteRequest){
        if(computerService.deleteComputer(deleteRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Сomputer has been sent to the warehouse!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }*/

    @GetMapping("/allComputers")
    @ApiOperation("Список компьютеров")
    public ResponseEntity<List<Computer>> computers(){
        return ResponseEntity.ok(computerService.getAllComputers());
    }


}
