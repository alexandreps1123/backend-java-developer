package com.cmanager.app.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EpisodeDTO", description = "Objeto da representação de episodio")
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDTO(
        @JsonProperty("id")
        @Schema(name = "id", description = "Id")
        String id,
        @JsonProperty("name")
        @Schema(name = "name", description = "Nome")
        String name,
        @JsonProperty("season")
        @Schema(name = "season", description = "Temporada")
        Integer season,
        @JsonProperty("number")
        @Schema(name = "number", description = "Episódio")
        Integer number
) {

}
