package Inventory.Management.dto;

import jakarta.validation.constraints.NotBlank;

public class WarehouseDTO {

    private Long id;

    @NotBlank(message = "Warehouse name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    public WarehouseDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}