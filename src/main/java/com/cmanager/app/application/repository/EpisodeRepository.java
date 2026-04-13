package com.cmanager.app.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.domain.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, String> {

    Page<Episode> findByShowId(String showId, Pageable pageable);

    Optional<Episode> findByIdIntegration(Integer idIntegration);

    void deleteByShowId(String showId);

    @Query("""
            select new com.cmanager.app.application.data.EpisodeAverageDTO(
                e.show.id,
                e.show.name,
                e.season,
                coalesce(avg(e.rating), 0)
            )
            from Episode e
            where lower(e.show.name) = lower(:showName)
            group by e.show.id, e.show.name, e.season
            order by e.season
            """)
    List<EpisodeAverageDTO> findAverageRatingBySeasonAndShowName(String showName);

}
