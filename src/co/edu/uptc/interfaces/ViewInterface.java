package co.edu.uptc.interfaces;

import java.util.List;
import co.edu.uptc.pojo.Product;

public interface ViewInterface {

    void showMenu();
    void showProducts(List<Product> products);
    void showMessage(String message);
    void showError(String error);
    String getInput(String prompt);

    void setPresenter(PresenterInterface presenter);
    void clearScreen();
}
