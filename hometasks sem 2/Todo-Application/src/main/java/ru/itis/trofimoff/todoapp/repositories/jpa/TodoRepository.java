package ru.itis.trofimoff.todoapp.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.trofimoff.todoapp.models.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    // updating todos by users id
    @Query("UPDATE Todo todo SET todo.text = :text WHERE id = :id")
    void update(@Param("text") String text, @Param("id") int id);

    // deleting todo_ by id
    void deleteById(int id);

    // saving todo_ into todo_ table, returns filled todo_
    Todo save(Todo todo);

    @Query(value = "SELECT users_todos.users_id, todos.text, todos.id, todos.group_id FROM users_todos JOIN todos ON users_todos.todos_id = todos.id WHERE users_todos.users_id = ?1", nativeQuery = true) // native - working?)
    public List<Todo> getUsersTodo(int userId);

    @Query(value = "SELECT users_todos.users_id, todos.text, todos.id, todos.group_id FROM users_todos JOIN todos ON users_todos.todos_id = todos.id WHERE users_todos.users_id = ?1 AND todos.group_id = ?2", nativeQuery = true) // native - working?)
    public List<Todo> getUsersTodoByGroup(int userId, int groupId);

    // fixme: move to the UserRep
    // changing user's stat - all
    @Query(value = "UPDATE users SET allTodos = alltodos + 1 WHERE id = ?1", nativeQuery =  true) // native - working?)
    void incrementUserStatAll(int id);

    // changing user's stat - done
    @Query(value = "UPDATE users SET doneTodos = doneTodos + 1 WHERE id = ?1", nativeQuery = true)
    void incrementUserStatDone(int id);

    // fixme:  inserting todo_'s & user's data into users_todo
    @Query(value = "INSERT INTO users_todos(users_id, todos_id) VALUES(?1, ?2)", nativeQuery = true) // native - working?)
    public void insertTodoIntoUsersTodo(int userId, int todoId);
}
