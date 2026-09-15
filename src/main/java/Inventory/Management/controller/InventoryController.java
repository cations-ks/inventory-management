package Inventory.Management.controller;

import Inventory.Management.dto.InventoryReportDTO;
import Inventory.Management.entity.Inventory;
import Inventory.Management.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Inventory addInventory(
            @RequestParam Long productId,
            @RequestParam Long warehouseId,
            @RequestParam int quantity) {

        return inventoryService.addInventory(productId, warehouseId, quantity);
    }

    @PostMapping("/stock-in")
    public Inventory stockIn(
            @RequestParam Long productId,
            @RequestParam Long warehouseId,
            @RequestParam int quantity) {

        return inventoryService.stockIn(productId, warehouseId, quantity);
    }

    @PostMapping("/stock-out")
    public Inventory stockOut(
            @RequestParam Long productId,
            @RequestParam Long warehouseId,
            @RequestParam int quantity) {

        return inventoryService.stockOut(productId, warehouseId, quantity);
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{productId}/{warehouseId}")
    public Inventory getInventory(
            @PathVariable Long productId,
            @PathVariable Long warehouseId) {

        return inventoryService.getInventory(productId, warehouseId);
    }

    @GetMapping("/low-stock")
    public List<Inventory> getLowStockItems() {
        return inventoryService.getLowStockItems();
    }

    @GetMapping("/report")
    public InventoryReportDTO getInventoryReport() {
        return inventoryService.getInventoryReport();
    }

    @GetMapping("/report/csv")
    public String exportInventoryCSV() throws IOException {
        return inventoryService.exportInventoryToCSV();
    }
}