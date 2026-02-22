package co.edu.uptc.model;

public class ProductNode {

    private Product product;
    private ProductNode next;

    public ProductNode(Product product) {
        this.product = product;
        this.next = null;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductNode getNext() {
        return next;
    }

    public void setNext(ProductNode next) {
        this.next = next;
    }
}