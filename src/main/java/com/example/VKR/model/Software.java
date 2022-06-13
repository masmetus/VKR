package com.example.VKR.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "software")
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2)
    @Column(name = "name", unique = true)
    private String Name;

    @OneToMany(mappedBy = "software", cascade = CascadeType.ALL )
    private List<InstalledSoftware> installedSoftwareList;


    public Software() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<InstalledSoftware> getInstalledSoftwareList() {
        return installedSoftwareList;
    }

    public void setInstalledSoftwareList(List<InstalledSoftware> installedSoftwareList) {
        this.installedSoftwareList = installedSoftwareList;
    }
}
