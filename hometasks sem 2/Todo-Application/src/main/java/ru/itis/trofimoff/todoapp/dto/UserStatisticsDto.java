package ru.itis.trofimoff.todoapp.dto;

public class UserStatisticsDto {
    public int allTodos;
    public int doneTodos;

    public UserStatisticsDto(int allTodos, int doneTodos) {
        this.allTodos = allTodos;
        this.doneTodos = doneTodos;
    }

    public int getAllTodos() {
        return allTodos;
    }

    public int getDoneTodos() {
        return doneTodos;
    }
}
