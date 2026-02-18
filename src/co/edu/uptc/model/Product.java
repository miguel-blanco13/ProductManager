package co.edu.uptc.model; 

public class Product {

    private String decription;
    private double price;
    private String unitOfMeasure;

    public Product(String decription, double price, String unitOfMeasure) {
        this.decription = decription;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public String toString() {
        return "Product{" +
                "decription='" + decription + '\'' +
                ", price=" + price +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                '}';      
    }

}