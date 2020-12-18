package com.example.info2020.Final_Project.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="author")
    private User author;
    private LocalDate creationDate;
    @Column(length = 200)
    private String comment;
    @ManyToOne
    @JoinColumn(name="comments")
    private Post post;

}
