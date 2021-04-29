package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PopularItem {
    @JsonProperty("adult")
    public boolean adult;
    @JsonProperty("backdrop_path")
    public String backdropPath;
    @JsonProperty("genre_ids")
    public List<Integer> genreIds;
    @JsonProperty("id")
    public int id;
    @JsonProperty("original_language")
    public String originalLanguage;
    @JsonProperty("original_title")
    public String originalTitle;
    @JsonProperty("overview")
    public String overview;
    @JsonProperty("popularity")
    public double popularity;
    @JsonProperty("poster_path")
    public String posterPath;
    @JsonProperty("release_date")
    public String releaseDate;
    @JsonProperty("title")
    public String title;
    @JsonProperty("video")
    public boolean video;
    @JsonProperty("vote_average")
    public double voteAverage;
    @JsonProperty("vote_count")
    public int voteCount;
}
