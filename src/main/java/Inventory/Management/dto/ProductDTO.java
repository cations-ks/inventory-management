package Inventory.Management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "SKU is required")
    private String sku;

    @PositiveOrZero(message = "Price cannot be negative")
    private double price;

    @Min(value = 0, message = "Minimum stock cannot be negative")
    private int minimumStock;

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public double getPrice() {
        return price;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }
}