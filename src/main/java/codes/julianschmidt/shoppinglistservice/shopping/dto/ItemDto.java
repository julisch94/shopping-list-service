package codes.julianschmidt.shoppinglistservice.shopping.dto;

import org.springframework.core.style.ToStringCreator;

public class ItemDto {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("title", title)
                .toString();
    }
}
