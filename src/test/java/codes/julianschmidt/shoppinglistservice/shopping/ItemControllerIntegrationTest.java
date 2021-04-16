package codes.julianschmidt.shoppinglistservice.shopping;

import static codes.julianschmidt.shoppinglistservice.shopping.ItemController.ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("The user should be able to create a shopping list item.")
    void shouldAddIdOnCreation() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "Hello World").toString()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value("Hello World"));
    }

    @Test
    @DisplayName("The user should be able to retrieve all shopping list items.")
    void shouldCreateItems() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "my item").toString()));
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "my second item").toString()));

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is(200))
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].title").value("my item"))
                .andExpect(jsonPath("[1].id").value(2))
                .andExpect(jsonPath("[1].title").value("my second item"));
    }

    @Test
    @DisplayName("The user should be able to delete a shopping list item.")
    void shouldDeleteItem() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "my item").toString()));
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "my second item").toString()));

        mockMvc.perform(delete(ENDPOINT + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204));

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().is(200))
                .andExpect(jsonPath("[0].id").value(2))
                .andExpect(jsonPath("[0].title").value("my second item"));
    }

    @Test
    @DisplayName("The user should see an error when trying to delete a non-existing item.")
    void deleteShouldReturnErrorWhenNotFound() throws Exception {
        mockMvc.perform(delete(ENDPOINT + "/42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(status().reason("Item with id '42' not found."));
    }

    @Test
    @DisplayName("The user should be able update a shopping list item.")
    void shouldUpdateItem() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "my item").toString()));

        mockMvc.perform(put(ENDPOINT + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "new title").toString()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value("new title"));
    }

    @Test
    @DisplayName("The user should see an error when trying to update a non-existing item.")
    void shouldReturnErrorWhenItemNotFound() throws Exception {
        mockMvc.perform(put(ENDPOINT + "/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JSONObject().put("title", "new title").toString()))
                .andExpect(status().is(404))
                .andExpect(status().reason("Item with id '5' not found."));
    }


}
