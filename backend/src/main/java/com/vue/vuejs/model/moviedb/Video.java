package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Video {
    @JsonProperty("id")
    public int id;
    @JsonProperty("results")
    public List<VideoItem> videoItems;
}
