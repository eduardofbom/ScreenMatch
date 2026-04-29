package br.com.eduardofbom.ScreenMatch.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown=true)
public record DataSeason (@JsonAlias("Season") Integer season,
                          @JsonAlias("Episodes") ArrayList<DataEpisode> episodesList) {
}
