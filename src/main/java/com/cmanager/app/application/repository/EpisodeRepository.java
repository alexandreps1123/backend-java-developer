package com.cmanager.app.application.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cmanager.app.application.domain.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, String> {

    Page<Episode> findByShowId(String showId, Pageable pageable);

    Optional<Episode> findByIdIntegration(Integer idIntegration);

    void deleteByShowId(String showId);

}
