package service.impl;

import model.Item;
import model.SortBy;
import repository.InventoryRepository;
import service.InventoryService;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository = new InventoryRepository();

    @Override
    public void addItem(String brand, String category, int price) {
        validate(brand, category);
        inventoryRepository.addItem(category, brand, price);
    }

    @Override
    public void addInventory(String brand, String category, int quantity) {
        validate(brand, category);
        inventoryRepository.addInventory(category, brand, quantity);
    }

    @Override
    public List<String> searchItems(Map<String, List<String>> filters, Integer priceFrom, Integer priceTo, SortBy sortBy) {
        if (priceFrom != null && priceTo != null && priceFrom > priceTo) {
            throw new IllegalArgumentException("Invalid price range: from > to");
        }

        List<Item> items = inventoryRepository.getAllItems();

        if (filters != null) {
            for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();

                if (key.equalsIgnoreCase("brand")) {
                    items = items.stream()
                            .filter(i -> values.contains(i.getBrand()))
                            .collect(Collectors.toList());
                } else if (key.equalsIgnoreCase("category")) {
                    items = items.stream()
                            .filter(i -> values.contains(i.getCategory()))
                            .collect(Collectors.toList());
                }
            }
        }

        if (priceFrom != null) {
            items = items.stream()
                    .filter(i -> i.getPrice() >= priceFrom)
                    .collect(Collectors.toList());
        }

        if (priceTo != null) {
            items = items.stream()
                    .filter(i -> i.getPrice() <= priceTo)
                    .collect(Collectors.toList());
        }

        if (sortBy != null) {
            switch (sortBy) {
                case PRICE_DESC -> items.sort(Comparator.comparingInt(Item::getPrice).reversed());
                case QUANTITY_ASC -> items.sort(Comparator.comparingInt(Item::getQuantity));
                case QUANTITY_DESC -> items.sort(Comparator.comparingInt(Item::getQuantity).reversed());
                default -> items.sort(Comparator.comparingInt(Item::getPrice));
            }
        } else {
            items.sort(Comparator.comparingInt(Item::getPrice));
        }

        return items.stream().map(Item::toString).collect(Collectors.toList());
    }

    private void validate(String brand, String category) {
        if (brand == null || brand.isEmpty())
            throw new IllegalArgumentException("Brand cannot be null or empty");
        if (category == null || category.isEmpty())
            throw new IllegalArgumentException("Category cannot be null or empty");
    }
}