package ru.kirdmt.tasksystem.enumeration;

import lombok.Getter;

@Getter
public enum Status {

    IN_PROCESS_STATUS ("В процессе"),
    IN_WAITING_STATUS("В ожидании"),
    COMPLEATED_STATUS ("Завершено"),
    CLOSED_STATUS ("Закрыто"),
    REJECTED_STATUS ("Отклонено");

    private String statusValue;

    Status(String statusValue) {
        this.statusValue = statusValue;
    }
}
