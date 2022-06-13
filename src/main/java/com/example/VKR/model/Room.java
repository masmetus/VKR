package com.example.VKR.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true)
    private String roomNumber;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Workplace> workplaces;

    public List<Workplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<Workplace> workplaces) {
        this.workplaces = workplaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room() {
    }

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
