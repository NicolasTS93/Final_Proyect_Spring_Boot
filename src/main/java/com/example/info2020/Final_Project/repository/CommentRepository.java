package com.example.info2020.Final_Project.repository;

import com.example.info2020.Final_Project.DAO.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
