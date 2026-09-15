package Inventory.Management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String sku;

    private double price;

    private int minimumStock;

    public Product() {
    }

    public Product(String name, String sku, double price, int minimumStock) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.minimumStock = minimumStock;
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