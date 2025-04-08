package repository;

import model.Item;

import java.util.*;

public class InventoryRepository {
    private final Map<String, Map<String, Item>> inventory = new HashMap<>();

    public void addItem(String category, String brand, int price) {
        inventory.putIfAbsent(category, new HashMap<>());
        inventory.get(category).put(brand, new Item(brand, category, price, 0));
    }

    public void addInventory(String category, String brand, int quantity) {
        Map<String, Item> brandMap = inventory.get(category);
        if (brandMap != null && brandMap.containsKey(brand)) {
            Item item = brandMap.get(brand);
            item.setQuantity(item.getQuantity() + quantity);
        }
    }

    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        for (Map<String, Item> brandMap : inventory.values()) {
            result.addAll(brandMap.values());
        }
        return result;
    }
}
