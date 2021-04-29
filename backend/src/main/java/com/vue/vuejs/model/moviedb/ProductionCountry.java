package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCountry {
    @JsonProperty("iso_3166_1")
    public String iso_3166_1;
    @JsonProperty("name")
    public String name;
}
