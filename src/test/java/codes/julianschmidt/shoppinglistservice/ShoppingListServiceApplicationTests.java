package codes.julianschmidt.shoppinglistservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ShoppingListServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddIdOnCreation() throws Exception {
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "Hello World"))
                .andExpect(content().string("{\"id\":1,\"title\":\"Hello World\"}"));
    }

    @Test
    void shouldCreateItems() throws Exception {
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my second item"));

        String expected = "[{\"id\":1,\"title\":\"my item\"},{\"id\":2,\"title\":\"my second item\"}]";
        mockMvc.perform(get("/item"))
                .andExpect(content().string(expected));
    }

    @Test
    void shouldDeleteItem() throws Exception {
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my second item"));

        mockMvc.perform(delete("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"));

        String expected = "[{\"id\":2,\"title\":\"my second item\"}]";
        mockMvc.perform(get("/item"))
                .andExpect(content().string(expected));
    }

    @Test
    void shouldUpdateItem() throws Exception {
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));

        mockMvc.perform(put("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "new title")
                .param("id", "1"))
                .andExpect(content().string("{\"id\":1,\"title\":\"new title\"}"));
    }

    @Test
    void shouldReturnErrorWhenItemNotFound() throws Exception {
        mockMvc.perform(put("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "new title")
                .param("id", "1"))
                .andExpect(status().is(404))
                .andExpect(status().reason("Item not found."));
    }

    @Test
    void shouldFindAllItems() throws Exception {
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));

        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "Hello World"));

        String expected = "[{\"id\":1,\"title\":\"my item\"},{\"id\":2,\"title\":\"Hello World\"}]";
        mockMvc.perform(get("/item"))
                .andExpect(content().string(expected));
    }
}
