package co.edu.uptc.model; 

public class Product {

    private String description;
    private double price;
    private String unitOfMeasure;

    public Product(String description, double price, String unitOfMeasure) {
        this.description = description;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                '}';      
    }

}