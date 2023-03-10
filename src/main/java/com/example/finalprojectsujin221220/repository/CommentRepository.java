package com.example.finalprojectsujin221220.repository;

import com.example.finalprojectsujin221220.domain.entity.Comment;
import com.example.finalprojectsujin221220.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByPost(Post post, Pageable pageable);

}
