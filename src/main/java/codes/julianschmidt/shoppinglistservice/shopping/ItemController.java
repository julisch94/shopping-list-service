package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import codes.julianschmidt.shoppinglistservice.shopping.dto.CreateItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.dto.UpdateItemDto;
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
    public Item createItem(CreateItemDto item) {
        return service.createItem(item);
    }

    @GetMapping(path = ENDPOINT)
    public List<Item> findAll() {
        return service.findAll();
    }

    @PutMapping(path = ENDPOINT)
    public Item updateItem(UpdateItemDto item) {
        return service.updateItem(item);
    }

    @DeleteMapping(path = ENDPOINT)
    public void deleteItem(long id) {
        service.deleteItem(id);
    }

}
