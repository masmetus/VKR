package com.example.VKR.dto.some.computer;

public class ChangeComputerRequest {

    private Long id;
    private String inv;

    public ChangeComputerRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }
}
