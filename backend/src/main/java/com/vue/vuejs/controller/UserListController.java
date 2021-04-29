package com.vue.vuejs.controller;

import com.vue.vuejs.model.postgres.UserList;
import com.vue.vuejs.model.postgres.UserListMovie;
import com.vue.vuejs.model.moviedb.Movie;
import com.vue.vuejs.repository.UserListMovieRepository;
import com.vue.vuejs.repository.UserListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("list")
public class UserListController {

    private final UserListRepository userListRepository;
    private final UserListMovieRepository userListMovieRepository;

    public UserListController(UserListRepository userListRepository,
                              UserListMovieRepository userListMovieRepository) {
        this.userListRepository = userListRepository;
        this.userListMovieRepository = userListMovieRepository;
    }

    @PostMapping()
    private ResponseEntity<UserList> createList(@RequestBody UserList userList) {
        return new ResponseEntity<>(userListRepository.save(userList), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<UserList>> getLists() {
        return new ResponseEntity<>(userListRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    private ResponseEntity<List<UserListMovie>> getListMovies(@PathVariable Integer id) {
        UserList userList = userListRepository.findOneById(id);

        if (userList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userListMovieRepository.findAllByUserList(userList), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteList(@PathVariable Integer id) {
        UserList userList = userListRepository.getOne(id);

        if (userList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userListRepository.delete(userList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    private ResponseEntity<?> addMovieToList(@PathVariable Integer id, @RequestBody Movie movie) {

        UserListMovie m = userListMovieRepository.findOneByMovieIdAndUserListId(Integer.valueOf(movie.getMovieId()), id);

        if (m != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        try {
            return userListRepository.findById(id).map((userList -> {
                UserListMovie b = new UserListMovie();
                b.setMovieId(Integer.valueOf(movie.getMovieId()));
                b.setUserList(userList);
                userListMovieRepository.save(b);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            })).orElseThrow(() -> new Exception("PostId " + id + " not found"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{listId}/movie/{movieId}")
    public ResponseEntity<?> deleteMovieFromList(@PathVariable(value = "listId") Integer listId,
                                         @PathVariable(value = "movieId") Integer movieId) {
        UserListMovie userListMovie = userListMovieRepository.findOneByMovieIdAndUserListId(movieId, listId);

        if (userListMovie == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userListMovieRepository.delete(userListMovie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



