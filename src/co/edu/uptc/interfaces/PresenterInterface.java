package co.edu.uptc.interfaces;

public interface PresenterInterface {

    void addProduct();
    void listProducts();
    void listProductsSorted();
    void deleteProducts();

    void setView(ViewInterface view);
    void setModel(ModelInterface model);
    void start();
}
