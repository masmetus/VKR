package com.example.VKR.dto.some.workplse;

import com.example.VKR.model.Computer;

public class CreateWorkplaceRequest {

    private Long id;

    private String number;

    private Computer computer;

    public CreateWorkplaceRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
