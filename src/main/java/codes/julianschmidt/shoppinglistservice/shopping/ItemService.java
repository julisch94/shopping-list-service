package codes.julianschmidt.shoppinglistservice.shopping;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import codes.julianschmidt.shoppinglistservice.shopping.dto.ItemDto;
import codes.julianschmidt.shoppinglistservice.shopping.exception.ItemNotFoundException;
import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@Component
public class ItemService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item createItem(ItemDto itemDto) {
        LOG.debug("Creating new item {} ...", itemDto);
        var toBeCreated = new Item(itemDto.getTitle());

        var item = repository.save(toBeCreated);
        LOG.info("New item created successfully: {}.", item);
        return item;
    }

    public List<Item> findAll() {
        LOG.debug("Finding all items ...");
        List<Item> items = repository.findAll();

        LOG.info("Found {} items.", items.size());
        return items;
    }

    public void deleteItem(long id) {
        LOG.debug("Deleting item with ID '{}' ...", id);
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException("Deleting item failed", id);
        }

        repository.deleteById(id);
        LOG.info("Item with ID '{}' was deleted successfully.", id);
    }

    public Item updateItem(long id, ItemDto itemDto) {
        LOG.debug("Updating item with ID '{}' using {} ...", id, itemDto);
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException("Updating item failed", id);
        }
        var toBeUpdated = new Item(id, itemDto.getTitle());

        var item = repository.save(toBeUpdated);
        LOG.info("Item with ID '{}' successfully updated to {}.", id, item);
        return item;
    }

}
