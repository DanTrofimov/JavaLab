package ru.itis.trofimoff.todoapp.services.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.converters.StringGroupConverter;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.repositories.jpa.GroupRepository;
import ru.itis.trofimoff.todoapp.repositories.jpa.TodoRepository;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StringGroupConverter stringGroupConverter;


    @Override
    public void addUsersTodo(TodoDto todoDto, int userId, String rights) {
        System.out.println(stringGroupConverter.convert(rights));
        Todo todo = new Todo(todoDto);
        if (todo.getText().trim().equals("")) return;
        switch (rights) {
            case "admin":
                /* fixme: пофиксить, основываясь на том, что объяснял Марсель
                    Todo_ - не сохранена
                    Group - не сохранена
                    1) сохранить Todo_
                    2) достать группу
                    3) ей положить Todo_
                    4) сохранить группу
                 */

//                Group adminGroup = groupRepository.findById(2).get(); // if null throw
                Group adminGroup = stringGroupConverter.convert(rights);

                todo.setGroup(adminGroup);

                Todo generatedAdminTodo = todoRepository.save(todo);

                System.out.println(generatedAdminTodo);

                todoRepository.insertTodoIntoUsersTodo(userId, generatedAdminTodo.getId());
                todoRepository.incrementUserStatAll(userId);
                break;
            case "users" :
//                Group userGroup = groupRepository.findById(1).get(); // if null throw
                Group userGroup = stringGroupConverter.convert(rights);
                todo.setGroup(userGroup);

                Todo generatedUsersTodo = todoRepository.save(todo);

                System.out.println(generatedUsersTodo);
                System.out.println(userId);
                todoRepository.insertTodoIntoUsersTodo(userId, generatedUsersTodo.getId());
                todoRepository.incrementUserStatAll(userId);
                break;
            default:
        }
    }

    @Override
    public void deleteTodo(int todoId, int userId) {
        todoRepository.removeUserBinding(userId, todoId);
        todoRepository.removeById(todoId);
        todoRepository.incrementUserStatDone(userId);
    }

    @Override
    public void addTodo(TodoDto todoDto, Group group) {
        Todo todo = new Todo(todoDto);
        if (!todo.getText().trim().equals("")) {
            todoRepository.save(todo);
        }
        todoDto.setId(todo.getId());
    }

    @Override
    public List<Todo> getUserTodos(int userId) {
        return todoRepository.getUsersTodo(userId);
    }

    @Override
    public List<Todo> getUserTodosWithPagination(int userId, int page, int size) {
        int offset = size * page;
        return this.todoRepository.getUsersTodoWithPagination(userId, size, offset); // id, limit, offset
    }

    @Override
    public List<Todo> getUserTodosByGroup(int userId, int groupId) {
        return todoRepository.getUsersTodoByGroup(userId, groupId);
    }

    @Override
    public int getUsersTodosAmount(int userId) {
        return this.todoRepository.getUsersTodosAmount(userId);
    }

    @Override
    public void updateTodo(TodoDto todoDto) {
        Todo todo = new Todo(todoDto);
        todoRepository.update(todo.getText(), todo.getId());
    }
}
