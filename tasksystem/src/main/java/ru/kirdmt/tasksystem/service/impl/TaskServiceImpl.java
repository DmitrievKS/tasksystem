package ru.kirdmt.tasksystem.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kirdmt.tasksystem.entity.Comment;
import ru.kirdmt.tasksystem.entity.Task;
import ru.kirdmt.tasksystem.enumeration.Priority;
import ru.kirdmt.tasksystem.enumeration.Status;
import ru.kirdmt.tasksystem.repository.CommentRepo;
import ru.kirdmt.tasksystem.repository.TaskRepo;
import ru.kirdmt.tasksystem.service.TaskService;

import java.util.LinkedList;

@Slf4j
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Task getTask(Integer taskId) {

        if (taskRepo.existsById(taskId)){
            Task task = taskRepo.findById(taskId).orElseThrow();
            return task;
        } else {
            log.info("Tasks id is incorrect: " + taskId);
            return null;
        }
    }

    @Override
    public LinkedList<Task> getTasksBy(String name, Integer code) {

        Integer creatorCode = 1;
        Integer responsibleCode = 2;

        if (code.equals(creatorCode)){
            return taskRepo.findByCreator(name);
        } else if (code.equals(responsibleCode)){
            return taskRepo.findByResponsible(name);
        } else
            log.info("Tasks data is incorrect.");
            return null;
    }

    @Override
    public Boolean addTask(Task task) {

        if(isCorrectEnumType("Priority", task.getPriority()) &&
                isCorrectEnumType("Status", task.getStatus())){
            log.info("adding Task");
            taskRepo.save(task);
            return true;
        }
        else {
            log.info("Tasks is incorrect");
            return false;
        }
    }

    @Override
    public Boolean deleteTask(Integer taskId) {

        if (taskRepo.existsById(taskId)){
            taskRepo.deleteById(taskId);
            return true;
        } else {
            log.info("Tasks id is incorrect: " + taskId);
            return false;
        }
    }

    @Override
    public Boolean editTask(Task task) {

        Task remouteTask = taskRepo.findById(task.getTask_id()).orElseThrow();

        if (taskRepo.existsById(task.getTask_id()) &&
                isCorrectEnumType("Priority", task.getPriority()) &&
                isCorrectEnumType("Status", task.getStatus()) &&
                isEditPermitted(remouteTask.getResponsibleid())) {
            taskRepo.save(task);
            return true;

        } else {
                log.info("Tasks is incorrect.");
                return false;
            }
    }

    @Override
    public Boolean addComment(Integer taskId, Comment comment) {

        if(taskRepo.existsById(taskId)){
            commentRepo.save(comment);
            return true;
        } else {
            log.info("Comment or taskID is incorrect.");
            return false;
        }
    }

    @Override
    public LinkedList<Comment> getComments(Integer taskId) {

        if(taskRepo.existsById(taskId)){
            return commentRepo.findByTaskid(taskId);
        } else {
            log.info("Comment or taskID is incorrect.");
            return null;
        }
    }

    private static boolean isCorrectEnumType(String enumName,String value) {

        if (enumName.equals("Priority")) {
            try {
                Enum.valueOf(Priority.class, value);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
    } else if (enumName.equals("Status")) {
            try {
                Enum.valueOf(Status.class, value);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return false;
    }

    private boolean isEditPermitted(String userId) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication.getName().equals(userId) |
                    authentication.getAuthorities().toString().toLowerCase().contains("admin")) {
                return true;
            } else
                return false;
        }

}
