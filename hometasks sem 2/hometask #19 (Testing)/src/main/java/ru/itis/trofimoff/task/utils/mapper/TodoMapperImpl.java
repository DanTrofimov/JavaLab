package ru.itis.trofimoff.task.utils.mapper;

public class TodoMapperImpl implements TodoMapper {

    private final String CRITERIA_IMPORTANT = "IMPORTANT";
    private final String CRITERIA_FEATURE = "FEATURE";

    @Override
    public String mapTodo(String todoText) {
        if (todoText.contains(CRITERIA_IMPORTANT)) return "important todo";
        if (todoText.contains(CRITERIA_FEATURE)) return "todo delivers feature";
        return "unclassified";
    }
}
