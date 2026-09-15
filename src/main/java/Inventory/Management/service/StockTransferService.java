package Inventory.Management.service;

import Inventory.Management.entity.Inventory;
import Inventory.Management.entity.Warehouse;
import Inventory.Management.repository.InventoryRepository;
import Inventory.Management.repository.WarehouseRepository;
import Inventory.Management.exception.InsufficientStockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockTransferService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;

    public StockTransferService(
            InventoryRepository inventoryRepository,
            WarehouseRepository warehouseRepository) {

        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public void transferStock(
            Long productId,
            Long fromWarehouseId,
            Long toWarehouseId,
            int quantity) {

        Inventory sourceInventory = inventoryRepository
                .findByProductIdAndWarehouseId(productId, fromWarehouseId)
                .orElseThrow(() -> new RuntimeException("Source inventory not found"));

        if (sourceInventory.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock");
        }

        Warehouse toWarehouse = warehouseRepository.findById(toWarehouseId)
                .orElseThrow(() -> new RuntimeException("Destination warehouse not found"));

        Inventory destinationInventory = inventoryRepository
                .findByProductIdAndWarehouseId(productId, toWarehouseId)
                .orElse(new Inventory(
                        sourceInventory.getProduct(),
                        toWarehouse,
                        0
                ));

        sourceInventory.setQuantity(
                sourceInventory.getQuantity() - quantity
        );

        destinationInventory.setQuantity(
                destinationInventory.getQuantity() + quantity
        );

        inventoryRepository.save(sourceInventory);
        inventoryRepository.save(destinationInventory);
    }
}