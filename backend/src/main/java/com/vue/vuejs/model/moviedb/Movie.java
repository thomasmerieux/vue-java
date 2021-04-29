package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Movie {

    @JsonProperty("movie_id")
    private String movieId;
}
