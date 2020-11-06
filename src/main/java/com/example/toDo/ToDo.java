package com.example.toDo;

public class ToDo {
    private String text;
    private ToDoStatus status;

    public ToDo(String text, ToDoStatus status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ToDoStatus getStatus() {
        return status;
    }

    public void setStatus(ToDoStatus status) {
        this.status = status;
    }
}
