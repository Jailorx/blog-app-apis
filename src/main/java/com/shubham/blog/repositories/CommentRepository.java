package com.shubham.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
