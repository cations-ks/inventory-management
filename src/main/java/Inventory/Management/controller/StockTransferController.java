package Inventory.Management.controller;

import Inventory.Management.service.StockTransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class StockTransferController {

    private final StockTransferService stockTransferService;

    public StockTransferController(StockTransferService stockTransferService) {
        this.stockTransferService = stockTransferService;
    }

    @PostMapping("/transfer")
    public String transferStock(
            @RequestParam Long productId,
            @RequestParam Long fromWarehouseId,
            @RequestParam Long toWarehouseId,
            @RequestParam int quantity) {

        stockTransferService.transferStock(
                productId,
                fromWarehouseId,
                toWarehouseId,
                quantity
        );

        return "Stock transferred successfully";
    }
}