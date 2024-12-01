package ru.kirdmt.tasksystem.entity;

//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.kirdmt.tasksystem.enumeration.Priority;
import ru.kirdmt.tasksystem.enumeration.Status;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String creator;
    private String responsible;
    private String responsibleid;

    public Task(String title, String description, String status, String priority, String creator, String responsible, String responsibleid) {

        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.creator = creator;
        this.responsible = responsible;
        this.responsibleid = responsibleid;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "title=" + title +
                ", description=" + description +
                ", status=" + status +
                ", priority=" + priority +
                ", creator=" + creator +
                ", responsible=" + responsible +
                ", responsibleid=" + responsibleid +
                '}';
    }

}
