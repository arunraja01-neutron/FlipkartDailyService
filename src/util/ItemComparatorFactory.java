package util;

import model.Item;
import java.util.Comparator;

public class ItemComparatorFactory {
    public static Comparator<Item> getComparator(String orderBy, boolean asc) {
        Comparator<Item> comparator;
        switch (orderBy.toLowerCase()) {
            case "price":
                comparator = Comparator.comparingInt(Item::getPrice);
                break;
            case "itemqty":
                comparator = Comparator.comparingInt(Item::getQuantity);
                break;
            default:
                comparator = Comparator.comparing(Item::getBrand); // fallback
        }
        return asc ? comparator : comparator.reversed();
    }
}
