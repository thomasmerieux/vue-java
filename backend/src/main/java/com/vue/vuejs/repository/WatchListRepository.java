package com.vue.vuejs.repository;

import com.vue.vuejs.model.postgres.FavoriteMovie;
import com.vue.vuejs.model.postgres.WatchListMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WatchListRepository extends JpaRepository<WatchListMovie, Integer> {

    WatchListMovie getOneByMovieId(Integer movieId);
}
