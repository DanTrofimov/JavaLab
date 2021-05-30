package ru.itis.trofimoff.task.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.itis.trofimoff.task.models.Todo;
import ru.itis.trofimoff.task.repository.jdbc.TodoJdbcRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES,
        beanName = "dataSource",
        provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY,
        refresh = AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
class TodoRepositoriesTests {
    @Autowired
    private TodoJdbcRepository todoJdbcRepository;

    @Autowired
    private TodoRepository todoRepository;

    // Arrange
    public static List<Todo> expectedAfterSavingTodos() {
        List<Todo> expected = new ArrayList<>();
        expected.add(new Todo(1, "Example todo"));
        expected.add(new Todo(2, "Test todo"));
        expected.add(new Todo(3, "Saved todo"));
        return expected;
    }

    public static List<Todo> expectedTodos() {
        List<Todo> expected = new ArrayList<>();
        expected.add(new Todo(1, "Example todo"));
        expected.add(new Todo(2, "Test todo"));
        return expected;
    }

    public static List<Todo> expectedAfterUpdatingTodos() {
        List<Todo> expected = new ArrayList<>();
        expected.add(new Todo(1, "Example todo"));
        expected.add(new Todo(2, "Updated todo"));
        return expected;
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("when JDBC repositories work")
    class ForJDBC {
        @Test
        public void correctly_returns_todos() {
            List<Todo> actual = todoJdbcRepository.findAllTodos();
            assertThat(actual, is(equalTo(expectedAfterUpdatingTodos())));
        }
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("when JDBC repositories work")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ForJPA {
        @Test
        @Order(1)
        public void correctly_saving_todo() {
            todoRepository.save(new Todo(3,"Saved todo"));
            List<Todo> actual = todoRepository.findAll();
            assertThat(actual, is(equalTo(expectedAfterSavingTodos())));
        }

        @Test
        @Order(2)
        public void correctly_deleting_todo() {
            todoRepository.deleteById(3);
            List<Todo> actual = todoRepository.findAll();
            assertThat(actual, is(equalTo(expectedTodos())));
        }

        @Test
        @Order(3)
        public void correctly_updating_todo() {
            todoRepository.update( "Updated todo", 2);
            List<Todo> actual = todoRepository.findAll();
            assertThat(actual, is(equalTo(expectedAfterUpdatingTodos())));
        }
    }
}