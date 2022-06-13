package com.example.VKR.controllers;


import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.model.Request;
import com.example.VKR.service.RequestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/req")
public class RequestController {

    @Autowired
    RequestService requestService;


    @PostMapping("/newRequest")
    @ApiOperation("Создать заявку о неисправности")
    public ResponseEntity<MessageResponse> newReq(@Valid @RequestBody Request request){
        if(requestService.addRequest(request.getDescriptionOfTheProblem(),request.getInstalledSoftware().getId() , request.getTeacher())){
            return ResponseEntity.ok(new MessageResponse("Request create successfully!"));
        }
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @GetMapping("/allRequest")
    @ApiOperation("Вывести список всех заявок")
    public ResponseEntity<List<Request>> requestList(){
        return ResponseEntity.ok(requestService.getAllRequest());
    }
}
