# Inventory & Stock Management API

A backend REST API for managing products, suppliers, warehouses, and inventory using Java, Spring Boot, MySQL, and JPA/Hibernate.

## Technologies Used

- Java 25
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST API
- Postman
- Java File I/O

## Features

- Product CRUD operations
- Supplier management
- Warehouse management
- Inventory management
- Stock-in
- Stock-out
- Stock transfer between warehouses
- Low-stock detection
- Inventory reports
- CSV inventory report export
- Product search by name
- Product search by SKU
- Input validation
- Global exception handling
- Insufficient stock handling
- Transaction management
- DTOs
- Java Streams

## Project Structure

```text
src/main/java/Inventory/Management
│
├── controller
│   ├── ProductController.java
│   ├── SupplierController.java
│   ├── WarehouseController.java
│   ├── InventoryController.java
│   └── StockTransferController.java
│
├── service
│   ├── ProductService.java
│   ├── SupplierService.java
│   ├── WarehouseService.java
│   ├── InventoryService.java
│   └── StockTransferService.java
│
├── repository
│   ├── ProductRepository.java
│   ├── SupplierRepository.java
│   ├── WarehouseRepository.java
│   └── InventoryRepository.java
│
├── entity
│   ├── Product.java
│   ├── Supplier.java
│   ├── Warehouse.java
│   └── Inventory.java
│
├── dto
│   ├── ProductDTO.java
│   ├── SupplierDTO.java
│   ├── WarehouseDTO.java
│   └── InventoryReportDTO.java
│
└── exception
    ├── InsufficientStockException.java
    └── GlobalExceptionHandler.java