package com.example.VKR.repository;

import com.example.VKR.model.Computer;
import com.example.VKR.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer>  findByWorkplace(Workplace workplace);

    Optional<Computer> findByIdAndWorkplaceIsNull (Long id);
}
