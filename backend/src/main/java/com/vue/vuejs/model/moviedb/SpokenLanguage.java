package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpokenLanguage {
    @JsonProperty("english_name")
    public String englishName;
    @JsonProperty("iso_639_1")
    public String iso_639_1;
    @JsonProperty("name")
    public String name;
}
