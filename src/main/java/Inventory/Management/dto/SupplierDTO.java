package Inventory.Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SupplierDTO {

    private Long id;

    @NotBlank(message = "Supplier name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    public SupplierDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}