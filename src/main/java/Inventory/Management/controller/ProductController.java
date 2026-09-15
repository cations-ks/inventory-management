package Inventory.Management.controller;

import Inventory.Management.dto.ProductDTO;
import Inventory.Management.entity.Product;
import Inventory.Management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search/name")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @GetMapping("/search/sku")
    public List<Product> searchBySku(@RequestParam String sku) {
        return productService.searchBySku(sku);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {

        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}