package com.example.VKR.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "installed_software")
public class InstalledSoftware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;

    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseEnd;

    @ManyToOne
    @JoinColumn(name = "engineer_id")
    private User user;

    private Date installationDate;

    @Column(name = "work_status")
    private boolean workStatus;


    public InstalledSoftware() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLicenseStart() {
        return licenseStart;
    }

    public void setLicenseStart(Date licenseStart) {
        this.licenseStart = licenseStart;
    }

    public Date getLicenseEnd() {
        return licenseEnd;
    }

    public void setLicenseEnd(Date licenseEnd) {
        this.licenseEnd = licenseEnd;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public boolean isWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(boolean workStatus) {
        this.workStatus = workStatus;
    }
}
