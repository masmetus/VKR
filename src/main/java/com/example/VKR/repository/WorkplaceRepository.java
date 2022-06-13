package com.example.VKR.repository;

import com.example.VKR.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

}
