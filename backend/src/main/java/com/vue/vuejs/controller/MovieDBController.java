package com.vue.vuejs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vue.vuejs.model.moviedb.MovieDetail;
import com.vue.vuejs.model.moviedb.MovieSearch;
import com.vue.vuejs.model.moviedb.Popular;
import com.vue.vuejs.model.moviedb.PopularItem;
import com.vue.vuejs.model.postgres.FavoriteMovie;
import com.vue.vuejs.model.postgres.WatchListMovie;
import com.vue.vuejs.repository.FavoriteRepository;
import com.vue.vuejs.repository.WatchListRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MovieDBController {

    public final OkHttpClient client = new OkHttpClient();

    private final FavoriteRepository favoriteRepository;
    private final WatchListRepository watchListRepository;

    public MovieDBController(FavoriteRepository favoriteRepository,
                             WatchListRepository watchListRepository) {
        this.favoriteRepository = favoriteRepository;
        this.watchListRepository = watchListRepository;
    }

    @GetMapping("search/{searchTerm}")
    private ResponseEntity<MovieSearch> searchMovies(@PathVariable String searchTerm) {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/search/movie?api_key=31ddda5ccbf5a1b61b6dbda7b145c317&language=en-US&query=" + searchTerm)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String r = Objects.requireNonNull(response.body()).string();
            ObjectMapper objectMapper = new ObjectMapper();
            MovieSearch entity = objectMapper.readValue(r, MovieSearch.class);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    private ResponseEntity<MovieDetail> getMovieDetail(@PathVariable String id) {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/" + id +
                        "?api_key=31ddda5ccbf5a1b61b6dbda7b145c317&append_to_response=videos,credits")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String r = Objects.requireNonNull(response.body()).string();

            ObjectMapper objectMapper = new ObjectMapper();
            MovieDetail entity = objectMapper.readValue(r, MovieDetail.class);

            WatchListMovie wlm = watchListRepository.getOneByMovieId(entity.getId());
            FavoriteMovie fm = favoriteRepository.getOneByMovieId(entity.getId());

            entity.setFavorite(fm != null);
            entity.setWatchList(wlm != null);

            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("popular")
    private ResponseEntity<List<PopularItem>> getPopularMovies() {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/popular?api_key=31ddda5ccbf5a1b61b6dbda7b145c317")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String r = Objects.requireNonNull(response.body()).string();
            ObjectMapper objectMapper = new ObjectMapper();
            Popular entity = objectMapper.readValue(r, Popular.class);
            return new ResponseEntity<>(entity.getResults(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
