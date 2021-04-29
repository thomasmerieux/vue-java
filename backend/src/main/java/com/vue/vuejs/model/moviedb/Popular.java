package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Popular {
    @JsonProperty("page")
    public int page;
    @JsonProperty("results")
    public List<PopularItem> results;
    @JsonProperty("total_pages")
    public int totalPages;
    @JsonProperty("total_results")
    public int totalResults;
}
