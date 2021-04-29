package com.vue.vuejs.repository;

import com.vue.vuejs.model.postgres.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Integer> {

    UserList findOneById(Integer id);
}
