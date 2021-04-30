package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.List;

import org.springframework.stereotype.Component;

import codes.julianschmidt.shoppinglistservice.shopping.dto.ItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.exception.ItemNotFoundException;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@Component
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item createItem(ItemDto item) {
        var newItem = new Item(item.getTitle());
        return repository.save(newItem);
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public void deleteItem(long id) {
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException("Deleting item failed", id);
        }
        repository.deleteById(id);
    }

    public Item updateItem(long id, ItemDto itemDto) {
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException("Updating item failed", id);
        }
        var item = new Item(id, itemDto.getTitle());
        return repository.save(item);
    }

}
