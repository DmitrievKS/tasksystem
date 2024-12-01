package ru.kirdmt.tasksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirdmt.tasksystem.entity.Comment;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    LinkedList<Comment> findByTaskid(Integer taskId);
}
