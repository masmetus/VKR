package com.example.VKR.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String INV;

    @OneToOne(mappedBy = "computer")
    private Workplace workplace;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "computer")
    private List<InstalledSoftware> installedSoftwareList;

    private boolean isUsed;

    public Computer() {
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public List<InstalledSoftware> getInstalledSoftwareList() {
        return installedSoftwareList;
    }

    public void setInstalledSoftwareList(List<InstalledSoftware> installedSoftwareList) {
        this.installedSoftwareList = installedSoftwareList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getINV() {
        return INV;
    }

    public void setINV(String INV) {
        this.INV = INV;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

}
