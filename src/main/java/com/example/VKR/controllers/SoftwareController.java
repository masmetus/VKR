package com.example.VKR.controllers;


import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.dto.some.software.ChangeSoftRequest;
import com.example.VKR.dto.some.software.CreateSoftwareRequest;
import com.example.VKR.dto.some.general.DeleteRequest;
import com.example.VKR.model.Software;
import com.example.VKR.service.SoftwareService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/soft")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @PostMapping("/newSoftware")
    @ApiOperation("Добавить софт")
    public ResponseEntity<MessageResponse> newSoft(@Valid @RequestBody CreateSoftwareRequest createSoftwareRequest){
        if(softwareService.addSoftware(createSoftwareRequest.getName()))
            return ResponseEntity.ok(new MessageResponse("Software add successfully!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @PutMapping("/editSoftware")
    @ApiOperation("Редактировать софт")
    public ResponseEntity<MessageResponse> editSoft(@Valid @RequestBody ChangeSoftRequest changeSoftRequest){
        if(softwareService.changeSoftName(changeSoftRequest.getName(), changeSoftRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Name has been changed!"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @DeleteMapping("/delSoft")
    @ApiOperation("Удалить софт")
    public ResponseEntity<MessageResponse> delSoft(@Valid @RequestBody DeleteRequest deleteRequest){
        if(softwareService.deleteSoftware(deleteRequest.getId()))
            return ResponseEntity.ok(new MessageResponse("Soft has been deleted"));
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
    }

    @GetMapping("/allSoft")
    @ApiOperation("Вывести список софта")
    public ResponseEntity<List<Software>> softwareList(){
        return ResponseEntity.ok(softwareService.getAllSoftware());
    }


}
