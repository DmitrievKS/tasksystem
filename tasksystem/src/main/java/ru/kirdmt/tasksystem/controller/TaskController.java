package ru.kirdmt.tasksystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirdmt.tasksystem.entity.Comment;
import ru.kirdmt.tasksystem.entity.Task;
import ru.kirdmt.tasksystem.service.TaskService;

import java.util.LinkedList;

@Tag(name = "API methods")
@Slf4j
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Adding new task to system.")
    @ResponseStatus
    @PostMapping("api/v1/task/add")
    ResponseEntity<String> addTask(@RequestBody Task task) {

        if (taskService.addTask(task)) {
            return new ResponseEntity<>(
                    "Task is added.",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "Cannot add task: " + task.toString(),
                HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Deleting task from system by task id.")
    @ResponseStatus
    @GetMapping("api/v1/task/delete/{taskId}")
    ResponseEntity<String> deleteTask(@PathVariable Integer taskId)    {

        if (taskService.deleteTask(taskId)) {
            return new ResponseEntity<>(
                    "Task is deleted.",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "Cannot delete task with id: " + taskId,
                HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Getting task from system by task id.")
    @ResponseStatus
    @GetMapping("api/v1/task/view/{taskId}")
    ResponseEntity<Task> viewTask(@PathVariable Integer taskId) {

        Task task  = taskService.getTask(taskId);

        if (task != null) {
            return new ResponseEntity<>(
                    task,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                task,
                HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Editing existing task. Only admin or author can do.")
    @ResponseStatus
    @PutMapping("api/v1/task/edit") //ready
    ResponseEntity<String> editTask(@RequestBody Task task) {

        if (taskService.editTask(task)) {
            return new ResponseEntity<>(
                    "Task is edited.",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "Cannot edit task: " + task.toString(),
                HttpStatus.BAD_REQUEST);

    }

    @Operation(summary = "Getting all tasks from system where a certain creator.")
    @ResponseStatus
    @GetMapping("api/v1/task/viewallby/creator/{creator}")
    ResponseEntity<LinkedList<Task>> viewAllByCreator(@PathVariable String creator) {

        Integer creatorCode = 1;
        LinkedList<Task> taskList  = taskService.getTasksBy(creator, creatorCode);

        if (taskList != null) {
            return new ResponseEntity<>(
                    taskList,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                taskList,
                HttpStatus.BAD_REQUEST);

    }

    @Operation(summary = "Getting all tasks from system where a certain responsible.")
    @ResponseStatus
    @GetMapping("api/v1/task/viewallby/responsible/{responsible}")
    ResponseEntity<LinkedList<Task>> viewAllByResponsible(@PathVariable String responsible) {

        Integer responsibleCode = 2;
        LinkedList<Task> taskList  = taskService.getTasksBy(responsible, responsibleCode);

        if (taskList != null) {
            return new ResponseEntity<>(
                    taskList,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                taskList,
                HttpStatus.BAD_REQUEST);

    }

    @Operation(summary = "Comment some task by task id.")
    @ResponseStatus
    @PostMapping("api/v1/task/comment/{taskId}")
    ResponseEntity<String> addComment(@PathVariable Integer taskId, @RequestBody Comment comment)    {

        if (taskService.addComment(taskId, comment)) {
            return new ResponseEntity<>(
                    "Comment is added.",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "Cannot add comment: " + comment.toString(),
                HttpStatus.BAD_REQUEST);

    }

    @Operation(summary = "Get all tasks comments by task id.")
    @ResponseStatus
    @GetMapping("api/v1/comments/view/{taskId}")
    ResponseEntity<LinkedList<Comment>> viewComments(@PathVariable Integer taskId) {

        LinkedList<Comment> commentList =  taskService.getComments(taskId);

        if (commentList != null) {
            return new ResponseEntity<>(
                    commentList,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                commentList,
                HttpStatus.BAD_REQUEST);
    }

}
