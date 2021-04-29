package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieCollection {
@JsonProperty("id")
    public int id;
@JsonProperty("name")
    public String name;
@JsonProperty("poster_path")
    public String posterPath;
@JsonProperty("backdrop_path")
    public String backdropPath;
}
