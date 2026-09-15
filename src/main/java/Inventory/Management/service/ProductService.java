package Inventory.Management.service;

import Inventory.Management.dto.ProductDTO;
import Inventory.Management.entity.Product;
import Inventory.Management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO productDTO) {

        Product product = new Product(
                productDTO.getName(),
                productDTO.getSku(),
                productDTO.getPrice(),
                productDTO.getMinimumStock()
        );

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setSku(productDTO.getSku());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setMinimumStock(productDTO.getMinimumStock());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> searchBySku(String sku) {
        return productRepository.findBySkuContainingIgnoreCase(sku);
    }
}