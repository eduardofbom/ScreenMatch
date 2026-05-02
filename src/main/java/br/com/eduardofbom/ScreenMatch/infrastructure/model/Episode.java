package br.com.eduardofbom.ScreenMatch.infrastructure.model;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.time.LocalDate;

public class Episode {

    private Integer season;
    private String title;
    private LocalDate released;
    private Integer episode;
    private Double rating;

    public Episode(Integer season, String title, LocalDate released, Integer episode, Double rating) {
        this.season = season;
        this.title = title;
        this.released = released;
        this.episode = episode;
        this.rating = rating;
    }

    public Episode(Integer season, DataEpisode e) {
        this.season = season;
        this.title = e.title();
        try {
            this.released = LocalDate.parse(e.released());
        } catch (RuntimeException ex) {
            this.released = null;
        }
        this.episode = e.episode();
        try {
            this.rating = Double.valueOf(e.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "season=" + season +
                ", title='" + title + '\'' +
                ", released=" + released +
                ", episode=" + episode +
                ", rating=" + rating +
                '}';
    }
}
