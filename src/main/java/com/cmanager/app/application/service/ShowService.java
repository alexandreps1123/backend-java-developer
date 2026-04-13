package com.cmanager.app.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmanager.app.application.adapter.ShowAdapter;
import com.cmanager.app.application.data.ShowDTO;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.repository.ShowRepository;
import com.cmanager.app.core.data.PageResultResponse;
import com.cmanager.app.core.utils.Util;
import com.cmanager.app.integration.dto.ShowsRequestDTO;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowAdapter showAdapter;

    public ShowService(ShowRepository showRepository,
            ShowAdapter showAdapter) {
        this.showRepository = showRepository;
        this.showAdapter = showAdapter;
    }

    @Transactional
    public Show create(ShowsRequestDTO apiShow) {
        return showRepository.findByIdIntegration(apiShow.id())
                .orElseGet(() -> showRepository.save(showAdapter.toShow(apiShow)));
    }

    @Transactional(readOnly = true)
    public PageResultResponse<ShowDTO> list(String name, int page, int size, String sortField, String sortOrder) {
        final Pageable pageable = Util.getPageable(page, size, sortField, sortOrder);
        final String filter = name == null ? "" : name;
        Page<Show> result = showRepository.findByNameContainingIgnoreCase(filter, pageable);
        Page<ShowDTO> dtoPage = result.map(ShowDTO::convertEntity);
        return PageResultResponse.from(dtoPage);
    }

}
