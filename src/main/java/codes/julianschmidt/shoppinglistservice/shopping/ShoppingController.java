package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import codes.julianschmidt.shoppinglistservice.shopping.dto.ItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@RestController()
public class ShoppingController {

    private final ShoppingService service;

    public ShoppingController(ShoppingService service) {
        this.service = service;
    }

    @PostMapping("/item")
    @ResponseBody
    public Item createItem(ItemDto item) {
        return service.createItem(item);
    }

    @GetMapping("/item")
    public List<Item> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/item")
    public void deleteItem(long id) {
        service.deleteItem(id);
    }

}
