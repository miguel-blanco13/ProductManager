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
        if (head == null || head.getNext() == null) return getAllProducts();
        bubbleSortByName();
        return getAllProducts();
    }

    private void bubbleSortByName() {
        boolean swapped;
        do {
            swapped = sortPass();
        } while (swapped);
    }

    private boolean sortPass() {
        boolean swapped = false;
        ProductNode current = head;
        while (current.getNext() != null) {
            if (shouldSwap(current, current.getNext())) {
                swapProducts(current, current.getNext());
                swapped = true;
            }
            current = current.getNext();
        }
        return swapped;
    }

    private boolean shouldSwap(ProductNode a, ProductNode b) {
        String nameA = a.getProduct().getDescription().toLowerCase();
        String nameB = b.getProduct().getDescription().toLowerCase();
        return nameA.compareTo(nameB) > 0;
    }

    private void swapProducts(ProductNode a, ProductNode b) {
        Product temp = a.getProduct();
        a.setProduct(b.getProduct());
        b.setProduct(temp);
    }

    @Override
    public int deleteProductsByName(String searchTerm) {
        String term = searchTerm.toLowerCase();
        int deleted = deleteFromHead(term);
        deleted += deleteFromRest(term);
        return deleted;
    }

    private int deleteFromHead(String term) {
        int deleted = 0;
        while (head != null && head.getProduct().getDescription().toLowerCase().contains(term)) {
            head = head.getNext();
            size--;
            deleted++;
        }
        return deleted;
    }

    private int deleteFromRest(String term) {
        int deleted = 0;
        ProductNode current = head;
        while (current != null && current.getNext() != null) {
            if (current.getNext().getProduct().getDescription().toLowerCase().contains(term)) {
                current.setNext(current.getNext().getNext());
                size--;
                deleted++;
            } else {
                current = current.getNext();
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