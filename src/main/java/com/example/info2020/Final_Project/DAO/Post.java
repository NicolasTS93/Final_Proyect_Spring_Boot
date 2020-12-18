package com.example.info2020.Final_Project.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name="author")
    private User author;
    private boolean published;
    @OneToMany(mappedBy="post")
    private List<Comment> comments;



}
