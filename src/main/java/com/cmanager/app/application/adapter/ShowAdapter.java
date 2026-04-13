package com.cmanager.app.application.adapter;

import org.springframework.stereotype.Component;

import com.cmanager.app.application.domain.Show;
import com.cmanager.app.integration.dto.ShowsRequestDTO;

@Component
public class ShowAdapter {

    public Show toShow(ShowsRequestDTO apiShow) {
        if (apiShow == null) {
            return null;
        }
        
        Show s = new Show();
        s.setIdIntegration(apiShow.id());
        s.setName(apiShow.name());
        s.setType(apiShow.type());
        s.setLanguage(apiShow.language());
        s.setStatus(apiShow.status());
        s.setRuntime(apiShow.runtime());
        s.setAverageRuntime(apiShow.averageRuntime());
        s.setOfficialSite(apiShow.officialSite());
        s.setRating(apiShow.rating() != null ? apiShow.rating().average() : null);
        s.setSummary(apiShow.summary());
        return s;
    }
}
