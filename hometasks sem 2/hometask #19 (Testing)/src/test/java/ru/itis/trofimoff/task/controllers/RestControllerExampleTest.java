package ru.itis.trofimoff.task.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.itis.trofimoff.task.models.Todo;
import ru.itis.trofimoff.task.services.todo.TodoService;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName(("RestControllerExampleTest is working when"))
public class RestControllerExampleTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        when(todoService.findAllTodos()).thenReturn(Collections.singletonList(
                Todo.builder()
                        .id(1)
                        .text("Example todo")
                        .build()));

        when(todoService.saveTodo(
                Todo.builder()
                        .text("Test todo")
                        .build()
        )).thenReturn(Todo.builder().id(1).text("Test todo").build());

        when(todoService.updateTodo(
                Todo.builder()
                        .id(1)
                        .text("Test todo")
                        .build()
        )).thenReturn(Todo.builder().id(1).text("Test todo").build());

        when(todoService.findTodoById(1)).thenReturn(Todo.builder().id(1).text("Example todo").build());

        when(todoService.deleteTodo(1)).thenReturn(Todo.builder().id(1).text("Example todo").build());
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getTodos() is working")
    class ForGetTodos {
        @Test
        public void returns_todos() throws Exception {
            mockMvc.perform(get("/todos"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].text", is("Example todo")));
        }
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("postTodo() is working")
    class ForPostTodos {
        @Test
        public void saves_todo() throws Exception {
            mockMvc.perform(post("/todos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "  \"text\": \"Test todo\"\n" +
                            "}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.text", is("Test todo")));
        }
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("deleteTodo() is working")
    class ForDeleteTodo {
        @Test
        public void deleting_todo() throws Exception {
            mockMvc.perform(delete("/todos?todoId=1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.text", is("Example todo")));
        }
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("deleteTodo() is working")
    class ForUpdateTodo {
        @Test
        public void updating_todo() throws Exception {
            mockMvc.perform(put("/todos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "  \"id\": \"1\",\n" +
                            "  \"text\": \"Test todo\"\n" +
                            "}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.text", is("Test todo")));
        }
    }
}
