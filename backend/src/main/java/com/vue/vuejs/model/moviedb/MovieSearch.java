package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearch {
    @JsonProperty("results")
    private List<MovieSearchItem> movieSearchItems;
}
