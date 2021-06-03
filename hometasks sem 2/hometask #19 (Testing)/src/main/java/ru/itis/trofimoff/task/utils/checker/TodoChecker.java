package ru.itis.trofimoff.task.utils.checker;

import lombok.AllArgsConstructor;
import ru.itis.trofimoff.task.utils.EmptyStringException;
import ru.itis.trofimoff.task.utils.mapper.TodoMapper;

@AllArgsConstructor
public class TodoChecker {

    private final int CRITERIA_LENGTH = 10;
    private TodoMapper todoMapper;

    public Boolean isTodoLong(String todoText) {
        if (todoText.trim().length() == 0) throw new EmptyStringException();
        return todoText.length() > CRITERIA_LENGTH;
    }

    public String checkTodoStatus(String todoText) {
        return todoMapper.mapTodo(todoText);
    }
}