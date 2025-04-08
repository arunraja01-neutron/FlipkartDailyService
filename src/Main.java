import model.SortBy;
import service.InventoryService;
import service.impl.InventoryServiceImpl;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InventoryService service = new InventoryServiceImpl();

        service.addItem("Amul", "Milk", 100);
        service.addItem("Amul", "Curd", 50);
        service.addItem("Nestle", "Milk", 60);
        service.addItem("Nestle", "Curd", 90);

        service.addInventory("Amul", "Milk", 10);
        service.addInventory("Nestle", "Milk", 5);
        service.addInventory("Nestle", "Curd", 10);
        service.addInventory("Amul", "Milk", 10);
        service.addInventory("Amul", "Curd", 5);

        System.out.println("Search by brand Nestle:");
        Map<String, List<String>> filters = Map.of("brand", List.of("Nestle"));
        service.searchItems(filters, null, null, null).forEach(System.out::println);

        System.out.println("\nSearch by category Milk:");
        filters = Map.of("category", List.of("Milk"));
        service.searchItems(filters, null, null, null).forEach(System.out::println);

        System.out.println("\nSearch by category Milk sorted by price desc:");
        service.searchItems(filters, null, null, SortBy.PRICE_DESC).forEach(System.out::println);

        System.out.println("\nSearch by price range 70-100:");
        service.searchItems(null, 70, 100, null).forEach(System.out::println);

        System.out.println("\nSearch by category Milk and price range 70-100 sorted by price desc:");
        filters = Map.of("category", List.of("Milk"));
        service.searchItems(filters, 70, 100, SortBy.PRICE_DESC).forEach(System.out::println);
    }
}