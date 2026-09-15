package Inventory.Management.service;

import Inventory.Management.dto.WarehouseDTO;
import Inventory.Management.entity.Warehouse;
import Inventory.Management.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse createWarehouse(WarehouseDTO warehouseDTO) {

        Warehouse warehouse = new Warehouse(
                warehouseDTO.getName(),
                warehouseDTO.getLocation()
        );

        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse updateWarehouse(Long id, WarehouseDTO warehouseDTO) {

        Warehouse existingWarehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        existingWarehouse.setName(warehouseDTO.getName());
        existingWarehouse.setLocation(warehouseDTO.getLocation());

        return warehouseRepository.save(existingWarehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}