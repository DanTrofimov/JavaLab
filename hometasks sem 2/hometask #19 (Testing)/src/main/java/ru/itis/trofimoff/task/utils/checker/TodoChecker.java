package ru.itis.trofimoff.task.utils.checker;

import ru.itis.trofimoff.task.utils.EmptyStringException;

public class TodoChecker {

    private final int CRITERIA_LENGTH = 10;
    private final String CRITERIA_IMPORTANT = "IMPORTANT";

    public Boolean isTodoLong(String todoText) {
        if (todoText.trim().length() == 0) throw new EmptyStringException();
        return todoText.length() > CRITERIA_LENGTH;
    }

    public Boolean isTodoImportant(String todoText) {
        return todoText.contains(CRITERIA_IMPORTANT);
    }
}