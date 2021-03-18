package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import codes.julianschmidt.shoppinglistservice.shopping.dto.CreateItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.dto.UpdateItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@Component
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item createItem(CreateItemDto item) {
        Item newItem = new Item(item.getTitle());
        return repository.save(newItem);
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public void deleteItem(long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found.");
        }
        repository.deleteById(id);
    }

    public Item updateItem(UpdateItemDto item) {
        return repository.findById(item.getId())
                .map(foundItem -> new Item(foundItem.getId(), item.getTitle()))
                .map(repository::save)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found."));
    }

}
