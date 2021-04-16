package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import codes.julianschmidt.shoppinglistservice.shopping.dto.ItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@RestController()
public class ItemController {

    // protected for testing purposes only
    protected static final String ENDPOINT = "/items";

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping(path = ENDPOINT)
    @ResponseBody
    public Item createItem(@RequestBody ItemDto item) {
        return service.createItem(item);
    }

    @GetMapping(path = ENDPOINT)
    public List<Item> findAll() {
        return service.findAll();
    }

    @PutMapping(path = ENDPOINT + "/{id}")
    public Item updateItem(@PathVariable long id, @RequestBody ItemDto item) {
        return service.updateItem(id, item);
    }

    @DeleteMapping(path = ENDPOINT + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable long id) {
        service.deleteItem(id);
    }

}
