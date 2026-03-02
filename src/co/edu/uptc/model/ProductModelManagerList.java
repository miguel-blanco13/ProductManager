package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.pojo.Product;
import co.edu.uptc.pojo.ProductNode;

import co.edu.uptc.interfaces.ModelInterface;

public class ProductModelManagerList implements ModelInterface {

    private ProductNode head;
    private ProductNode tail;
    private int size;

    public ProductModelManagerList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void addProduct(Product product) {
        ProductNode newNode = new ProductNode(product);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>(size);
        ProductNode current = head;
        while (current != null) {
            result.add(current.getProduct());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public List<Product> getProductsSortedByName() {
        List<Product> sorted = getAllProducts();
        sorted.sort((a, b) -> a.getDescription().toLowerCase()
                .compareTo(b.getDescription().toLowerCase()));
        return sorted;
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
        while (head != null && matchesTerm(head, term)) {
            head = head.getNext();
            size--;
            deleted++;
        }
        if (head == null) tail = null;
        return deleted;
    }

    private int deleteFromRest(String term) {
        int deleted = 0;
        ProductNode current = head;
        while (current != null && current.getNext() != null) {
            if (matchesTerm(current.getNext(), term)) {
                current.setNext(current.getNext().getNext());
                size--;
                deleted++;
            } else {
                current = current.getNext();
            }
        }
        tail = findLastNode();
        return deleted;
    }

    private ProductNode findLastNode() {
        if (head == null) return null;
        ProductNode current = head;
        while (current.getNext() != null) current = current.getNext();
        return current;
    }

    private boolean matchesTerm(ProductNode node, String term) {
        return node.getProduct().getDescription().toLowerCase().equals(term);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getProductCount() {
        return size;
    }

    @Override
    public String exec() throws Exception {
        return "";
    }
}