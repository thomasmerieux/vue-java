package com.vue.vuejs.repository;

import com.vue.vuejs.model.postgres.UserList;
import com.vue.vuejs.model.postgres.UserListMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserListMovieRepository extends JpaRepository<UserListMovie, Integer> {

    List<UserListMovie> findAllByUserList(UserList userList);

    UserListMovie findOneByMovieIdAndUserListId(Integer userListId, Integer movieId);
}
