package codes.julianschmidt.shoppinglistservice.shopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;

@Entity
public class Item {

    @Id
    @GeneratedValue()
    private Long id;

    private String title;

    public Item() {
    }

    public Item(String title) {
        this.title = title;
    }

    public Item(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("title", title)
                .toString();
    }
}
