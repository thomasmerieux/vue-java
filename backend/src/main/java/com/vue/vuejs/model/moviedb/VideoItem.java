package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoItem {
    @JsonProperty("id")
    public String id;
    @JsonProperty("iso_639_1")
    public String iso_639_1;
    @JsonProperty("iso_3166_1")
    public String iso_3166_1;
    @JsonProperty("key")
    public String key;
    @JsonProperty("name")
    public String name;
    @JsonProperty("site")
    public String site;
    @JsonProperty("size")
    public int size;
    @JsonProperty("type")
    public String type;
}
