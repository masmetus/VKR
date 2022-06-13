package com.example.VKR.dto.some.room;

public class ChangeRoomRequest {

    private Long id;
    private String number;

    public ChangeRoomRequest() {
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
}
