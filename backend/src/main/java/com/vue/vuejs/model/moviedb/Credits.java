package com.vue.vuejs.model.moviedb;

import lombok.Data;

import java.util.List;

@Data
public class Credits {
    public int id;
    public List<Cast> cast;
    public List<Crew> crew;
}
