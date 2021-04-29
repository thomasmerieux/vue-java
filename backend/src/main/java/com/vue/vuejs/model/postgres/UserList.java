package com.vue.vuejs.model.postgres;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(schema = "vue", name = "user_list")
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "label")
    private String label;

    @JsonManagedReference
    @OneToMany(mappedBy = "userList", cascade = CascadeType.ALL)
    private Set<UserListMovie> userListMovies;
}
