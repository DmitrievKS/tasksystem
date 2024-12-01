package ru.kirdmt.tasksystem.enumeration;

import lombok.Getter;

@Getter
public enum Priority {

    HIGH_PRIORITY ("Высокий приоритет"),
    MEDIUM_PRIORITY ("Средний приоритет"),
    LOW_PRIORITY ("Низкий приоритет"),
    NO_PRIORITY ("Не приоритетная задача");

    private String priorityLevel;

    Priority(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}
