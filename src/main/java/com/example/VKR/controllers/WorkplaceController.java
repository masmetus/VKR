package com.example.VKR.controllers;


import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.dto.some.computer.CreateComputerRequest;
import com.example.VKR.dto.some.workplse.CreateWorkplaceRequest;
import com.example.VKR.model.Computer;
import com.example.VKR.model.Workplace;
import com.example.VKR.service.WorkplaceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/workplace")
@RestController
public class WorkplaceController {

    @Autowired
    WorkplaceService workplaceService;


    @PostMapping("/newWorkplace")
    @ApiOperation("Добавить рабоочие место")
    public ResponseEntity<MessageResponse> newWorkplace(@Valid @RequestBody Workplace workplace){
        if(workplaceService.addWorkplace(workplace.getNumber(), workplace.getRoom().getId(), workplace.getComputer().getId())) {
            return ResponseEntity.ok(new MessageResponse("Workplace add successfully!"));
        }
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @DeleteMapping("/delWorkplace")
    @ApiOperation("Удалить рабоочие место")
    public ResponseEntity<MessageResponse> delWorkplace(@Valid @RequestBody Workplace workplace){
        if(workplaceService.delWorkplace(workplace.getId())) {
            return ResponseEntity.ok(new MessageResponse("Workplace delete!"));
        }
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @PutMapping("/editWorkplace")
    @ApiOperation("Редактировать рабоочие место")
    public ResponseEntity<MessageResponse> editWorkplace(@Valid @RequestBody Workplace workplace){
        if(workplaceService.editWorkplace(workplace.getId(), workplace.getNumber(), workplace.getComputer().getId())) {
            return ResponseEntity.ok(new MessageResponse("Workplace edit successfully!"));
        }
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }


    @GetMapping("/allWorkplace")
    @ApiOperation("Вывести все рабоочие места")
    public ResponseEntity<List<Workplace>> workplaces() {
        return ResponseEntity.ok(workplaceService.getAllWorkplaces());
    }


}
