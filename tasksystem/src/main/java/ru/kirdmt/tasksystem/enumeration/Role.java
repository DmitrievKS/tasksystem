package ru.kirdmt.tasksystem.enumeration;

public enum Role {

    ADMIN ("admin"),
    USER ("user");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
