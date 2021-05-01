package ru.itis.trofimoff.todoapp.utils.pagination;

public interface PaginationUtil {
    int getPageAmount(int todosAmount, int pageSize);
}
