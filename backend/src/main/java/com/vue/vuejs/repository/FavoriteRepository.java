package com.vue.vuejs.repository;

import com.vue.vuejs.model.postgres.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteMovie, Integer> {


    FavoriteMovie getOneByMovieId(Integer movieId);
}
