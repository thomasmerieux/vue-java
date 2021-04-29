package com.vue.vuejs.model.postgres;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "vue", name = "user_list_movie", uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id" , "list_id"}))
public class UserListMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie_id")
    private Integer movieId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private UserList userList;




}
