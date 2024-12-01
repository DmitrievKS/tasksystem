package ru.kirdmt.tasksystem.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.kirdmt.tasksystem.entity.Task;
import ru.kirdmt.tasksystem.service.TaskService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void addTaskTestTrue() {

        Task task = new Task();

        when(taskService.addTask(task)).thenReturn(true);

        ResponseEntity expected = new ResponseEntity<>(
                "Task is added.",
                HttpStatus.OK);

        assertEquals(expected, taskController.addTask(task));
    }

    @Test
    void addTaskTestFalse() {

        Task task = new Task();

        when(taskService.addTask(task)).thenReturn(false);

        ResponseEntity expected = new ResponseEntity<>(
                "Cannot add task: " + task.toString(),
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.addTask(task));
    }

    @Test
    void deleteTaskTest() {
    }

    @Test
    void viewTaskTest() {
    }

    @Test
    void editTaskTrueTest() {

        Task task = new Task();

        when(taskService.editTask(task)).thenReturn(true);

        ResponseEntity expected = new ResponseEntity<>(
                "Task is edited.",
                HttpStatus.OK);

        assertEquals(expected, taskController.editTask(task));

    }

    @Test
    void editTaskFalseTest() {

        Task task = new Task();

        when(taskService.editTask(task)).thenReturn(false);

        ResponseEntity expected = new ResponseEntity<>(
                "Cannot edit task: " + task.toString(),
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.editTask(task));

    }

    @Test
    void viewAllByCreatorTest() {
    }

    @Test
    void viewAllByResponsibleTest() {
    }

    @Test
    void addCommentTest() {
    }

    @Test
    void viewCommentsTest() {
    }
}