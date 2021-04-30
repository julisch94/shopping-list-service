package codes.julianschmidt.shoppinglistservice.shopping.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message, long id) {
        super(String.format("%s - Reason: Item with id '%d' was not found", message, id));
    }


}
