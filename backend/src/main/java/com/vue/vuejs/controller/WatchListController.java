package com.vue.vuejs.controller;

import com.vue.vuejs.model.postgres.WatchListMovie;
import com.vue.vuejs.repository.WatchListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("watch-list")
public class WatchListController {

    private final WatchListRepository watchListRepository;

    public WatchListController(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    @GetMapping
    private List<WatchListMovie> getWatchListMovies() {
        return watchListRepository.findAll();
    }

    @PostMapping("{id}")
    private ResponseEntity<WatchListMovie> addMovieToWatchList(@PathVariable Integer id) {
        WatchListMovie watchListMovie = new WatchListMovie();
        watchListMovie.setMovieId(id);
        return new ResponseEntity<>(watchListRepository.save(watchListMovie), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> deleteMovieFromWatchList(@PathVariable Integer id) {
        WatchListMovie favorite = watchListRepository.getOneByMovieId(id);

        if (favorite == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        watchListRepository.delete(favorite);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("{id}")
    private ResponseEntity<Void> isFavorite(@PathVariable Integer id) {
        WatchListMovie favorite = watchListRepository.getOneByMovieId(id);
        if (favorite != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
