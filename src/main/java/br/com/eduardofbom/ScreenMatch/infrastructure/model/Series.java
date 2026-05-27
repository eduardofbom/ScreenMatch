package br.com.eduardofbom.ScreenMatch.infrastructure.model;

import br.com.eduardofbom.ScreenMatch.service.GeminiApiConsumption;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class Series {

    private String title;
    private double rating;
    private Integer quantSeasons;
    private GenreCategory genre;
    private List<String> actors;
    private String synopsis;
    private String posterUrl;

    public Series(DataSeries dataSeries) {
        this.title = dataSeries.title();
        this.rating = OptionalDouble.of(Double.valueOf(dataSeries.rating())).orElse(0.0);
        try {
            this.quantSeasons = Integer.valueOf(dataSeries.quantSeasons());
        } catch (NumberFormatException ex) {
            this.quantSeasons = 0;
        }
        this.genre = GenreCategory.fromString((dataSeries.genre().split(",")[0].trim()));
        this.actors = List.of(dataSeries.actors().split(","));
        this.synopsis = GeminiApiConsumption.getTranslate(dataSeries.synopsis());
        this.posterUrl = dataSeries.posterUrl();
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getQuantSeasons() {
        return quantSeasons;
    }
    public void setQuantSeasons(Integer quantSeasons) {
        this.quantSeasons = quantSeasons;
    }

    public GenreCategory getGenre() {
        return genre;
    }
    public void setGenre(GenreCategory genre) {
        this.genre = genre;
    }

    public List<String> getActors() {
        return actors;
    }
    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public String toString() {
        return "genre=" + genre +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", quantSeasons=" + quantSeasons +
                ", actors=" + actors +
                ", synopsis='" + synopsis + '\'' +
                ", posterUrl='" + posterUrl + '\'';
    }
}
