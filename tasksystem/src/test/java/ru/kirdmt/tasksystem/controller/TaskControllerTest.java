package ru.kirdmt.tasksystem.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.kirdmt.tasksystem.entity.Comment;
import ru.kirdmt.tasksystem.entity.Task;
import ru.kirdmt.tasksystem.enumeration.Priority;
import ru.kirdmt.tasksystem.enumeration.Status;
import ru.kirdmt.tasksystem.service.TaskService;

import java.util.LinkedList;

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
    void deleteTaskTestTrue() {

        Integer taskId = 1;

        when(taskService.deleteTask(taskId)).thenReturn(true);

        ResponseEntity expected = new ResponseEntity<>(
                "Task is deleted.",
                HttpStatus.OK);

        assertEquals(expected, taskController.deleteTask(taskId));
    }

    @Test
    void deleteTaskTestFalse() {

        Integer taskId = 1;

        when(taskService.deleteTask(taskId)).thenReturn(false);

        ResponseEntity expected = new ResponseEntity<>(
                "Cannot delete task with id: " + taskId,
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.deleteTask(taskId));

    }

    @Test
    void viewTaskTestTrue() {

        //if (task != null)

        Integer taskId = 1;
        Task task  = new Task("title", "description", Status.CLOSED_STATUS.getStatusValue(), Priority.HIGH_PRIORITY.getPriorityLevel(),  "creator",  "responsible",  "responsibleid");

        when(taskService.getTask(taskId)).thenReturn(task);

        ResponseEntity expected = new ResponseEntity<>(
                task,
                HttpStatus.OK);

        assertEquals(expected, taskController.viewTask(taskId));

    }
    @Test
    void viewTaskTestFalse() {

        //if (task != null)

        Integer taskId = 1;
        Task task  = null;

        when(taskService.getTask(taskId)).thenReturn(null);

        ResponseEntity expected = new ResponseEntity<>(
                task,
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.viewTask(taskId));
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
    void viewAllByCreatorTrueTest() {

        //if (taskList != null)

        Integer creatorCode = 1;
        String creator = "Someone";
        LinkedList<Task> taskList  = taskService.getTasksBy(creator, creatorCode);

        when(taskService.getTasksBy(creator, creatorCode)).thenReturn(taskList);

        ResponseEntity expected = new ResponseEntity<>(
                taskList,
                HttpStatus.OK);

        assertEquals(expected, taskController.viewAllByCreator(creator));

    }

    @Test
    void viewAllByCreatorFalseTest() {

        //if (taskList != null)

        Integer creatorCode = 1;
        String creator = "Someone";
        LinkedList<Task> taskList  = null;

        when(taskService.getTasksBy(creator, creatorCode)).thenReturn(null);

        ResponseEntity expected = new ResponseEntity<>(
                taskList,
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.viewAllByCreator(creator));

    }

    @Test
    void viewAllByResponsibleTrueTest() {

        //if (taskList != null)

        Integer responsibleCode = 2;
        String responsible = "Someone";
        LinkedList<Task> taskList  = taskService.getTasksBy(responsible, responsibleCode);

        when(taskService.getTasksBy(responsible, responsibleCode)).thenReturn(taskList);

        ResponseEntity expected = new ResponseEntity<>(
                taskList,
                HttpStatus.OK);

        assertEquals(expected, taskController.viewAllByResponsible(responsible));

    }

    @Test
    void viewAllByResponsibleFalseTest() {

        //if (taskList != null)

        Integer responsibleCode = 2;
        String responsible = "Someone";
        LinkedList<Task> taskList  = null;

        when(taskService.getTasksBy(responsible, responsibleCode)).thenReturn(null);

        ResponseEntity expected = new ResponseEntity<>(
                taskList,
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.viewAllByResponsible(responsible));

    }

    @Test
    void addCommentTrueTest() {

        Integer taskId = 1;
        Comment comment = new Comment();

        when(taskService.addComment(taskId, comment)).thenReturn(true);

        ResponseEntity expected = new ResponseEntity<>(
                "Comment is added.",
                HttpStatus.OK);

        assertEquals(expected, taskController.addComment(taskId, comment));
    }

    @Test
    void addCommentTrueFalse() {

        Integer taskId = 1;
        Comment comment = new Comment();

        when(taskService.addComment(taskId, comment)).thenReturn(false);

        ResponseEntity expected = new ResponseEntity<>(
                "Cannot add comment: " + comment.toString(),
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.addComment(taskId, comment));
    }

    @Test
    void viewCommentsTrueTest() {

        //if (commentList != null)

        Integer taskId = 2;
        LinkedList<Comment> commentList = taskService.getComments(taskId);

        when(taskService.getComments(taskId)).thenReturn(commentList);

        ResponseEntity expected = new ResponseEntity<>(
                commentList,
                HttpStatus.OK);

        assertEquals(expected, taskController.viewComments(taskId));
    }

    @Test
    void viewCommentsFalseTest() {

        //if (commentList != null)

        Integer taskId = 2;
        LinkedList<Comment> commentList = null;

        when(taskService.getComments(taskId)).thenReturn(null);

        ResponseEntity expected = new ResponseEntity<>(
                commentList,
                HttpStatus.BAD_REQUEST);

        assertEquals(expected, taskController.viewComments(taskId));
    }
}