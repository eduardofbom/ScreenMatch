package br.com.eduardofbom.ScreenMatch.infrastructure.model;

import java.util.List;

public class Series {

    private String title;
    private double rating;
    private Integer quantSeasons;
    private List<String> genre;
    private List<String> actors;
    private String synopsis;
    private String posterUrl;

    public Series(DataSeries dataSeries) {
        this.title = dataSeries.title();
        this.rating = Double.parseDouble(dataSeries.rating());
        this.quantSeasons = Integer.parseInt(dataSeries.quantSeasons());
        this.genre = List.of(dataSeries.genre().split(", "));
        this.actors = List.of(dataSeries.actors().split(", "));
        this.synopsis = dataSeries.synopsis();
        this.posterUrl = dataSeries.posterUrl();
    }

    public String getTitle() {
        return title;
    }
    public double getRating() {
        return rating;
    }
    public Integer getQuantSeasons() {
        return quantSeasons;
    }
    public List<String> getGenre() {
        return genre;
    }
    public List<String> getActors() {
        return actors;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public String getPosterUrl() {
        return posterUrl;
    }

    @Override
    public String toString() {
        return "Series{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", quantSeasons=" + quantSeasons +
                ", genre=" + genre +
                ", actors=" + actors +
                ", synopsis='" + synopsis + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}
