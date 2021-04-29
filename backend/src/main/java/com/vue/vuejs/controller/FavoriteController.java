package com.vue.vuejs.controller;

import com.vue.vuejs.model.postgres.FavoriteMovie;
import com.vue.vuejs.repository.FavoriteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("favorite")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping
    private List<FavoriteMovie> getFavoriteMovies() {
        return favoriteRepository.findAll();
    }

    @PostMapping("{id}")
    private ResponseEntity<FavoriteMovie> addMovieToFavorite(@PathVariable Integer id) {
        FavoriteMovie favorite = new FavoriteMovie();
        favorite.setMovieId(id);
        return new ResponseEntity<>(favoriteRepository.save(favorite), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> deleteMovieFromFavorite(@PathVariable Integer id) {
        FavoriteMovie favorite = favoriteRepository.getOneByMovieId(id);

        if (favorite == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        favoriteRepository.delete(favorite);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("{id}")
    private ResponseEntity<Void> isFavorite(@PathVariable Integer id) {
        FavoriteMovie favorite = favoriteRepository.getOneByMovieId(id);
        if (favorite != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
