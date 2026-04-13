package com.cmanager.app.application.adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;

@Component
public class EpisodeAdapter {

    public Episode toEpisode(EpisodeRequestDTO ep, Show show) {
        if (ep == null || ep.id() == null) {
            return null;
        }

        Episode e = new Episode();
        e.setIdIntegration(ep.id());
        e.setShow(show);
        e.setName(ep.name());
        e.setSeason(ep.season());
        e.setNumber(ep.number());
        e.setType(ep.type());
        e.setAirdate(parseDate(ep.airdate()));
        e.setAirtime(parseTime(ep.airtime()));
        e.setAirstamp(parseAirstamp(ep.airstamp()));
        e.setRuntime(ep.runtime());
        e.setRating(ep.rating() != null ? ep.rating().average() : null);
        e.setSummary(ep.summary());
        return e;
    }

    private LocalDate parseDate(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }

        return LocalDate.parse(date);
    }

    private LocalTime parseTime(String time) {
        if (time == null || time.isBlank()) {
            return null;
        }

        return LocalTime.parse(time);
    }

    private OffsetDateTime parseAirstamp(LocalDateTime airstamp) {
        if (airstamp == null) {
            return null;
        }
        
        return airstamp.atOffset(ZoneOffset.UTC);
    }
}
