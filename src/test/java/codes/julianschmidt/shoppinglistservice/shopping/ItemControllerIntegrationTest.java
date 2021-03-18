package codes.julianschmidt.shoppinglistservice.shopping;

import static codes.julianschmidt.shoppinglistservice.shopping.ItemController.ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddIdOnCreation() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "Hello World"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value("Hello World"));
    }

    @Test
    void shouldCreateItems() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my second item"));

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is(200))
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].title").value("my item"))
                .andExpect(jsonPath("[1].id").value(2))
                .andExpect(jsonPath("[1].title").value("my second item"));
    }

    @Test
    void shouldDeleteItem() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my second item"));

        mockMvc.perform(delete(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"));

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is(200))
                .andExpect(jsonPath("[0].id").value(2))
                .andExpect(jsonPath("[0].title").value("my second item"));
    }

    @Test
    void shouldUpdateItem() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));

        mockMvc.perform(put(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "new title")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value("new title"));
    }

    @Test
    void shouldReturnErrorWhenItemNotFound() throws Exception {
        mockMvc.perform(put(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "new title")
                .param("id", "1"))
                .andExpect(status().is(404))
                .andExpect(status().reason("Item not found."));
    }

    @Test
    void shouldFindAllItems() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "my item"));

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "Hello World"));

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is(200))
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].title").value("my item"))
                .andExpect(jsonPath("[1].id").value(2))
                .andExpect(jsonPath("[1].title").value("Hello World"));
    }

}
