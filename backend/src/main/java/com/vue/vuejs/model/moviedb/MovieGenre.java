package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieGenre {
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
}
