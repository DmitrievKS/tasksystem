package ru.kirdmt.tasksystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;
    private Integer taskid;
    private String author;
    private String comment;

    public Comment(Integer taskid, String author, String comment) {

        this.taskid = taskid;
        this.author = author;
        this.comment = comment;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "taskid=" + taskid +
                ", author=" + author +
                ", comment=" + comment +
                '}';
    }

}
