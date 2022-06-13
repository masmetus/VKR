package com.example.VKR.repository;

import com.example.VKR.model.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, Long> {
}
