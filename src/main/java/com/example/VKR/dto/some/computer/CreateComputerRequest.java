package com.example.VKR.dto.some.computer;

public class CreateComputerRequest {

    private String inv;

    public CreateComputerRequest(String inv) {
        this.inv = inv;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }


}
