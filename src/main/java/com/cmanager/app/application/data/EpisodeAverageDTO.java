package com.cmanager.app.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EpisodeAverageDTO", description = "Media de rating por show e temporada")
public record EpisodeAverageDTO(
        @Schema(name = "showId", description = "Id do show")
        String showId,
        @Schema(name = "showName", description = "Nome do show")
        String showName,
        @Schema(name = "season", description = "Temporada")
        Integer season,
        @Schema(name = "average", description = "Media de rating")
        Double average
) {
}
