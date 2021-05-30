package ru.itis.trofimoff.task.utils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itis.trofimoff.task.utils.checker.TodoChecker;
import ru.itis.trofimoff.task.utils.mapper.TodoMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;


@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("TodoChecker is working when")
@ExtendWith(MockitoExtension.class)
public class TodoCheckerTest {

    private TodoChecker todoChecker;

    @Mock
    private TodoMapper todoMapper;

    @BeforeEach
    public void setUp() {
        lenient().when(todoMapper.mapTodo("IMPORTANT create API")).thenReturn("important todo");
        lenient().when(todoMapper.mapTodo("IMPORTANT deploy staging")).thenReturn("important todo");
        lenient().when(todoMapper.mapTodo("FEATURE add OAuth")).thenReturn("todo delivers feature");
        lenient().when(todoMapper.mapTodo("FEATURE upgrade app to PWA")).thenReturn("todo delivers feature");
        lenient().when(todoMapper.mapTodo("Lorem ipsum dolor")).thenReturn("unclassified");
        lenient().when(todoMapper.mapTodo("Sed ut perspiciatis unde")).thenReturn("unclassified");
        todoChecker = new TodoChecker(todoMapper);
    }

    @DisplayName("isTodoLong() is working")
    @Nested
    class ForIsLong {
        @ParameterizedTest(name = "throws exception on empty string")
        @ValueSource(strings = {
                "     ",
                ""
        })
        public void on_empty_string_throws_exception(String emptySting) {
            assertThrows(EmptyStringException.class, () -> todoChecker.isTodoLong(emptySting));
        }

        @ParameterizedTest(name = "returns <true> on long strings")
        @ValueSource(strings = {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                "Sed ut perspiciatis unde omnis iste natus error sit"
        })
        public void on_long_string_returns_true(String longString) {
            assertTrue(todoChecker.isTodoLong(longString));
        }


        @ParameterizedTest(name = "returns <false> on short strings")
        @ValueSource(strings = {
                "Lorem",
                "Sed ut"
        })
        public void on_long_string_returns_false(String shortString) {
            assertFalse(todoChecker.isTodoLong(shortString));
        }
    }

    @DisplayName("checkTodoStatus() is working")
    @Nested
    class CheckTodoStatus {
        @ParameterizedTest(name = "returns <important todo> on important todos")
        @ValueSource(strings = {
                "IMPORTANT create API",
                "IMPORTANT deploy staging"
        })
        public void can_classify_important_tasks(String todoText) {
            assertEquals("important todo", todoChecker.checkTodoStatus(todoText));
        }

        @ParameterizedTest(name = "returns <feature> on feature delivering todos")
        @ValueSource(strings = {
                "FEATURE add OAuth",
                "FEATURE upgrade app to PWA"
        })
        public void can_classify_feature_tasks(String todoText) {
            assertEquals("todo delivers feature", todoChecker.checkTodoStatus(todoText));
        }


        @ParameterizedTest(name = "returns <unclassified> on unknown type if todo")
        @ValueSource(strings = {
                "Lorem ipsum dolor",
                "Sed ut perspiciatis unde"
        })
        public void can_handle_unclassified_todo(String todoText) {
            assertEquals("unclassified", todoChecker.checkTodoStatus(todoText));
        }
    }
}
