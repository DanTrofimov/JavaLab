package ru.itis.trofimoff.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatisticsDto {
    public int allTodos;
    public int doneTodos;
    public int needTodo;

    public UserStatisticsDto(int allTodos, int doneTodos) {
        this.allTodos = allTodos;
        this.doneTodos = doneTodos;
        this.needTodo = allTodos - doneTodos;
    }
}