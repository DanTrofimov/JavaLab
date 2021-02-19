package ru.itis.trofimoff.todoapp.models;

public class Todo {

    int id;
    String text;
    int groupId;

    public Todo(String text) {
        this.text = text;
    }

    public Todo(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Todo(int id, String text, int groupId) {
        this.id = id;
        this.text = text;
        this.groupId = groupId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
