package br.com.eduardofbom.ScreenMatch.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeries (@JsonAlias("Title") String title,
                          @JsonAlias("imdbRating") double rating,
                          @JsonAlias("totalSeasons") Integer quantSeasons){
}
