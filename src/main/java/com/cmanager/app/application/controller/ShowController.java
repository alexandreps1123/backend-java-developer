package com.cmanager.app.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmanager.app.application.data.ShowCreateRequest;
import com.cmanager.app.application.data.ShowDTO;
import com.cmanager.app.application.service.SyncService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shows")
@Tag(
        name = "ShowController",
        description = "API de sincronizacao de shows"
)
public class ShowController {

    private final SyncService syncService;

    public ShowController(SyncService syncService) {
        this.syncService = syncService;
    }

    @Operation(
            summary = "sync",
            description = "Sincroniza show (TVMaze) e persiste show + episodios",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sincronizacao realizada com sucesso")
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShowDTO> sync(@RequestBody @Valid ShowCreateRequest req) {
        final var dto = syncService.syncShow(req);
        return ResponseEntity.ok(dto);
    }

}
