package service;

import model.Item;
import model.SortBy;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    void addItem(String brand, String category, int price);
    void addInventory(String brand, String category, int quantity);
    List<String> searchItems(Map<String, List<String>> filters, Integer priceFrom, Integer priceTo, SortBy sortBy);
}
