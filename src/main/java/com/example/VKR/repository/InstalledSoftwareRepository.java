package com.example.VKR.repository;

import com.example.VKR.model.InstalledSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstalledSoftwareRepository extends JpaRepository<InstalledSoftware, Long> {
    InstalledSoftware findByComputerIdAndSoftwareId (Long computerId, Long softwareId);
}
