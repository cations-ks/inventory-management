package Inventory.Management.controller;

import Inventory.Management.dto.SupplierDTO;
import Inventory.Management.entity.Supplier;
import Inventory.Management.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public Supplier createSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierDTO supplierDTO) {

        return supplierService.updateSupplier(id, supplierDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return "Supplier deleted successfully";
    }
}