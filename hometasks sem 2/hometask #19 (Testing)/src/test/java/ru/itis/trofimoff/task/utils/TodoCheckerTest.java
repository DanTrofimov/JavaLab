package ru.itis.trofimoff.task.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.itis.trofimoff.task.utils.checker.TodoChecker;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("TodoChecker is working when")
public class TodoCheckerTest {
    private final TodoChecker todoChecker = new TodoChecker();

    @DisplayName("isTodoLong() is working")
    @Nested
    class ForIsLong {
        @ParameterizedTest(name = "throws exception on {0}")
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


}
