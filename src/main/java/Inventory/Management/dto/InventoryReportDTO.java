package Inventory.Management.dto;

public class InventoryReportDTO {

    private int totalItems;
    private double totalValue;
    private long lowStockItems;

    public InventoryReportDTO() {
    }

    public InventoryReportDTO(
            int totalItems,
            double totalValue,
            long lowStockItems) {

        this.totalItems = totalItems;
        this.totalValue = totalValue;
        this.lowStockItems = lowStockItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public long getLowStockItems() {
        return lowStockItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void setLowStockItems(long lowStockItems) {
        this.lowStockItems = lowStockItems;
    }
}