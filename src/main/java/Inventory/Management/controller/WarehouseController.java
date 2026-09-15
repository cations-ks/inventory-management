package Inventory.Management.controller;

import Inventory.Management.dto.WarehouseDTO;
import Inventory.Management.entity.Warehouse;
import Inventory.Management.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Warehouse createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.createWarehouse(warehouseDTO);
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Optional<Warehouse> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Warehouse updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseDTO warehouseDTO) {

        return warehouseService.updateWarehouse(id, warehouseDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return "Warehouse deleted successfully";
    }
}