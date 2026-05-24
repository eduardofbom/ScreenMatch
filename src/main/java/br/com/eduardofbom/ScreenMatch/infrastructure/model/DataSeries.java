package br.com.eduardofbom.ScreenMatch.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeries (@JsonAlias("Title") String title,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("totalSeasons") String quantSeasons,
                          @JsonAlias("Genre") String genre,
                          @JsonAlias("Actors") String actors,
                          @JsonAlias("Plot") String synopsis,
                          @JsonAlias("Poster") String posterUrl){
}
