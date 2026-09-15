package Inventory.Management.service;

import Inventory.Management.dto.InventoryReportDTO;
import Inventory.Management.entity.Inventory;
import Inventory.Management.entity.Product;
import Inventory.Management.entity.Warehouse;
import Inventory.Management.exception.InsufficientStockException;
import Inventory.Management.repository.InventoryRepository;
import Inventory.Management.repository.ProductRepository;
import Inventory.Management.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Inventory addInventory(Long productId, Long warehouseId, int quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(productId, warehouseId)
                .orElse(new Inventory(product, warehouse, 0));

        inventory.setQuantity(inventory.getQuantity() + quantity);

        return inventoryRepository.save(inventory);
    }

    public Inventory stockIn(Long productId, Long warehouseId, int quantity) {
        return addInventory(productId, warehouseId, quantity);
    }

    public Inventory stockOut(Long productId, Long warehouseId, int quantity) {

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        if (inventory.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock");
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);

        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventory(Long productId, Long warehouseId) {

        return inventoryRepository
                .findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    public List<Inventory> getLowStockItems() {

        List<Inventory> inventoryList = inventoryRepository.findAll();

        return inventoryList.stream()
                .filter(inventory ->
                        inventory.getQuantity() <= inventory.getProduct().getMinimumStock())
                .toList();
    }

    public InventoryReportDTO getInventoryReport() {

        List<Inventory> inventoryList = inventoryRepository.findAll();

        int totalItems = inventoryList.stream()
                .mapToInt(Inventory::getQuantity)
                .sum();

        double totalValue = inventoryList.stream()
                .mapToDouble(inventory ->
                        inventory.getQuantity() * inventory.getProduct().getPrice())
                .sum();

        long lowStockItems = inventoryList.stream()
                .filter(inventory ->
                        inventory.getQuantity() <= inventory.getProduct().getMinimumStock())
                .count();

        return new InventoryReportDTO(
                totalItems,
                totalValue,
                lowStockItems
        );
    }

    public String exportInventoryToCSV() throws IOException {

        List<Inventory> inventoryList = inventoryRepository.findAll();

        try (FileWriter writer = new FileWriter("inventory_report.csv")) {

            writer.append("Product,SKU,Warehouse,Quantity,Price,Total Value\n");

            for (Inventory inventory : inventoryList) {

                Product product = inventory.getProduct();
                Warehouse warehouse = inventory.getWarehouse();

                double totalValue =
                        inventory.getQuantity() * product.getPrice();

                writer.append(product.getName())
                        .append(",")
                        .append(product.getSku())
                        .append(",")
                        .append(warehouse.getName())
                        .append(",")
                        .append(String.valueOf(inventory.getQuantity()))
                        .append(",")
                        .append(String.valueOf(product.getPrice()))
                        .append(",")
                        .append(String.valueOf(totalValue))
                        .append("\n");
            }
        }

        return "CSV report generated successfully: inventory_report.csv";
    }
}