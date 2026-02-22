package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.interfaces.ModelInterface;

public class ProductModelManagerList implements ModelInterface{

    private ProductNode head;
    private int size;

    public ProductModelManagerList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addProduct(Product product) {
        ProductNode newNode = new ProductNode(product);

        if (head == null) {
            head = newNode;
        } else {
            ProductNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>();
        ProductNode current = head;

        while (current != null) {
            result.add(current.getProduct());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public List<Product> getProductsSortedByName() {
        if (head == null || head.getNext() == null) {
            return getAllProducts();
        }

        boolean swapped;
        do {
            swapped = false;
            ProductNode current = head;

            while (current.getNext() != null) {
                String nameA = current.getProduct().getDecription().toLowerCase();
                String nameB = current.getNext().getProduct().getDecription().toLowerCase();

                if (nameA.compareTo(nameB) > 0) {
                    Product temp = current.getProduct();
                    current.setProduct(current.getNext().getProduct());
                    current.getNext().setProduct(temp);
                    swapped = true;
                }
                current = current.getNext();
            }
        } while (swapped);

        return getAllProducts();
    }

    @Override
    public int deleteProductsByName(String searchTerm) {
        int deleted = 0;
        String term = searchTerm.toLowerCase();

        while (head != null && head.getProduct().getDecription().toLowerCase().contains(term)) {
            head = head.getNext();
            size--;
            deleted++;
        }

        if (head != null) {
            ProductNode current = head;
            while (current.getNext() != null) {
                String name = current.getNext().getProduct().getDecription().toLowerCase();
                if (name.contains(term)) {
                    current.setNext(current.getNext().getNext());
                    size--;
                    deleted++;
                } else {
                    current = current.getNext();
                }
            }
        }
        return deleted;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getProductCount() {
        return size;
    }
}