package com.cmanager.app.application.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmanager.app.application.domain.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {
    
    Page<Show> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<Show> findByIdIntegration(Integer idIntegration);
    
}
