package com.vue.vuejs.model.postgres;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "vue", name = "watch_list")
public class WatchListMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;
}
