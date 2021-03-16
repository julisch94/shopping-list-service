package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.List;

import org.springframework.stereotype.Component;

import codes.julianschmidt.shoppinglistservice.shopping.dto.ItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@Component
public class ShoppingService {

    private final ShoppingRepository repository;

    public ShoppingService(ShoppingRepository repository) {
        this.repository = repository;
    }

    public Item createItem(ItemDto item) {
        Item newItem = new Item(item.getTitle());
        return repository.save(newItem);
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public void deleteItem(long id) {
        repository.deleteById(id);
    }

}
