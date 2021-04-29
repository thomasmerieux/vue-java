package com.vue.vuejs.model.postgres;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "vue", name = "favorite")
public class FavoriteMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie_id")
    private Integer movieId;
}
