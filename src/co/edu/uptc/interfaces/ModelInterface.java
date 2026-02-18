package co.edu.uptc.interfaces;

import java.util.List;

import co.edu.uptc.model.Product;

public interface ModelInterface {

    void addProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductsSortedByName();
    int deleteProductsByName(String searchTerm);

    boolean isEmpty();
    int getProductCount();
}