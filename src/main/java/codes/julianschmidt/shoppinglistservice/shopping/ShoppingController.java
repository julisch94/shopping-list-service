package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import codes.julianschmidt.shoppinglistservice.shopping.dto.ItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@RestController()
public class ShoppingController {

    private final List<Item> items = new ArrayList<>();

    @PostMapping("/item")
    @ResponseBody
    public Item createItem(ItemDto item) {
        Item createdItem = new Item(item.getTitle());
        items.add(createdItem);
        return createdItem;
    }

    @GetMapping("/item")
    public List<Item> findAll() {
        return items;
    }

}
