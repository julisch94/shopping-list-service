package codes.julianschmidt.shoppinglistservice.shopping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codes.julianschmidt.shoppinglistservice.shopping.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
