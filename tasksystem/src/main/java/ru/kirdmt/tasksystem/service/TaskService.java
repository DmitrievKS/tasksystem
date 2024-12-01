package ru.kirdmt.tasksystem.service;

import ru.kirdmt.tasksystem.entity.Comment;
import ru.kirdmt.tasksystem.entity.Task;

import java.util.LinkedList;
import java.util.List;

public interface TaskService {

    Task getTask(Integer taskId);

    LinkedList<Task> getTasksBy(String name, Integer code);

    Boolean addTask(Task task);

    Boolean deleteTask(Integer taskId);

    Boolean editTask(Task task);

    Boolean addComment(Integer taskId, Comment comment);

    LinkedList<Comment> getComments(Integer taskId);

}
