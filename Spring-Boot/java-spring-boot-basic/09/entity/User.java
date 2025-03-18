package com.example.movieapp.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;
    
    private String role;
    
    @Column(name = "created_at")
    private Timestamp createdAt;

    // Self-referencing ManyToMany cho mối quan hệ theo dõi (Follows)
    @ManyToMany
    @JoinTable(
        name = "follows",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<User> following = new HashSet<>();
    
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

 
}
