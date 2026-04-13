package com.cmanager.app.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.service.EpisodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/episodes")
@Tag(
        name = "EpisodeController",
        description = "API de episodios"
)
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @Operation(
            summary = "average",
            description = "Media de rating por show e temporada",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
            }
    )
    @GetMapping("/average")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<EpisodeAverageDTO>> average(
            @Parameter(description = "Nome do show para filtro", example = "office")
            @RequestParam(name = "show") String show
    ) {
        return ResponseEntity.ok(episodeService.averageBySeason(show));
    }
}
