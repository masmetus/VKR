package com.example.VKR.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @ManyToOne
    @JoinColumn(name = "installed_software_id")
    private InstalledSoftware installedSoftware;

    private Date applicationDate;

    private Date applicationClosingDate;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User responsibleEngineer;

    private String descriptionOfTheProblem;

    private EStatus status;

    public Request() {
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public User getResponsibleEngineer() {
        return responsibleEngineer;
    }

    public void setResponsibleEngineer(User responsibleEngineer) {
        this.responsibleEngineer = responsibleEngineer;
    }

    public String getDescriptionOfTheProblem() {
        return descriptionOfTheProblem;
    }

    public void setDescriptionOfTheProblem(String descriptionOfTheProblem) {
        this.descriptionOfTheProblem = descriptionOfTheProblem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public InstalledSoftware getInstalledSoftware() {
        return installedSoftware;
    }

    public void setInstalledSoftware(InstalledSoftware installedSoftware) {
        this.installedSoftware = installedSoftware;
    }


    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApplicationClosingDate() {
        return applicationClosingDate;
    }

    public void setApplicationClosingDate(Date applicationClosingDate) {
        this.applicationClosingDate = applicationClosingDate;
    }
}
