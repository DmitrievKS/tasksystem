package ru.kirdmt.tasksystem.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirdmt.tasksystem.entity.Comment;
import ru.kirdmt.tasksystem.entity.Task;

import java.util.LinkedList;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

    LinkedList<Task> findByCreator(String creator);
    LinkedList<Task> findByResponsible(String responsible);

}

