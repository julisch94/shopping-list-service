package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import codes.julianschmidt.shoppinglistservice.shopping.dto.ItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@Component
public class ShoppingService {

    private final List<Item> items = new ArrayList<>();

    public Item createItem(ItemDto item) {
        Item newItem = new Item(item.getTitle());
        items.add(newItem);
        return newItem;
    }

    public List<Item> findAll() {
        return items;
    }
}
