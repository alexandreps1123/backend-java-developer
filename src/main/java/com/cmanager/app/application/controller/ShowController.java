package com.cmanager.app.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmanager.app.application.data.ShowCreateRequest;
import com.cmanager.app.application.data.ShowDTO;
import com.cmanager.app.application.service.ShowService;
import com.cmanager.app.application.service.SyncService;
import com.cmanager.app.core.data.PageResultResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    private final ShowService showService;

    public ShowController(SyncService syncService, ShowService showService) {
        this.syncService = syncService;
        this.showService = showService;
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

    @Operation(
            summary = "list",
            description = "Lista shows com paginacao, filtro por nome e ordenacao",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
            }
    )
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PageResultResponse<ShowDTO>> list(
            @Parameter(description = "Nome do show para filtro", example = "office")
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @Parameter(description = "Numero da pagina (inicia em 0)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Quantidade de registros por pagina", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size,
            @Parameter(description = "Direcao da ordenacao (ASC ou DESC)", example = "ASC")
            @RequestParam(value = "sortOrder", defaultValue = "ASC") String sortOrder
    ) {
        String sortField = "name";
        return ResponseEntity.ok(showService.list(name, page, size, sortField, sortOrder));
    }

}
