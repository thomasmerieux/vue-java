package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCompany {
    @JsonProperty("id")
    public int id;
    @JsonProperty("logo_path")
    public String logoPath;
    @JsonProperty("name")
    public String name;
    @JsonProperty("origin_country")
    public String originCountry;
}
