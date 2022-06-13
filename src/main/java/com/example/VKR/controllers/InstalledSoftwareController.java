package com.example.VKR.controllers;


import com.example.VKR.dto.auth.response.MessageResponse;
import com.example.VKR.model.InstalledSoftware;
import com.example.VKR.model.Software;
import com.example.VKR.service.InstalledSoftwareService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/instSoft")
public class InstalledSoftwareController {

    @Autowired
    InstalledSoftwareService installedSoftwareService;

//    @PostMapping("/installSoft")
//    @ApiOperation("Установить ПО")
//    public ResponseEntity<MessageResponse> instSoft(@Valid @RequestBody InstalledSoftware installedSoftware) {
//        if (installedSoftwareService.installSoft(installedSoftware.getId(), installedSoftware.getSoftware().getId(), installedSoftware.getComputer().getId(),
//                installedSoftware.getInstallationDate(), installedSoftware.getUser())) {
//            return ResponseEntity.ok(new MessageResponse("Request create successfully!"));
//        }
//        else
//            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
//
//    }

    @DeleteMapping("/delInstSoft")
    @ApiOperation("Удалить софт с компа")
    public ResponseEntity<MessageResponse> delInstSoft(@Valid @RequestBody InstalledSoftware installedSoftware){
        if(installedSoftwareService.delInstallSoft(installedSoftware.getId())){
            return ResponseEntity.ok(new MessageResponse("Software uninstall!"));
        }
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @PutMapping("editInstallSoft")
    @ApiOperation("Редактирование установленного софта")
    public ResponseEntity<MessageResponse> editInstallSoft(@Valid @RequestBody InstalledSoftware installedSoftware){
        if(installedSoftwareService.editInstallSoft(installedSoftware.getSoftware().getId(), installedSoftware.getLicenseStart(), installedSoftware.getLicenseEnd())){
            return ResponseEntity.ok(new MessageResponse("Information about Installed Software change successful!"));
        }
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
    }

    @GetMapping("/allInstallSoft")
    @ApiOperation("Получить список установленного ПО")
    public ResponseEntity<List<InstalledSoftware>> installSoftList(){
        return ResponseEntity.ok(installedSoftwareService.getAllInstalledSoftware());
    }
}
