package ru.itis.trofimoff.todoapp.utils.pagination;

import org.springframework.stereotype.Component;

@Component
public class PaginationUtilImpl implements PaginationUtil {
    public int getPageAmount(int todosAmount, int pageSize) {
        return todosAmount % pageSize == 0 ? todosAmount / pageSize : todosAmount / pageSize + 1;
    }
}
