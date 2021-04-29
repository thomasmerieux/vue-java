package com.vue.vuejs.model.moviedb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Crew {
    @JsonProperty("adult")
    public boolean adult;
    @JsonProperty("gender")
    public int gender;
    @JsonProperty("id")
    public int id;
    @JsonProperty("known_for_department")
    public String knownForDepartment;
    @JsonProperty("name")
    public String name;
    @JsonProperty("original_name")
    public String originalName;
    @JsonProperty("popularity")
    public double popularity;
    @JsonProperty("profile_path")
    public String profilePath;
    @JsonProperty("credit_id")
    public String credit_id;
    @JsonProperty("department")
    public String department;
    @JsonProperty("job")
    public String job;
}
