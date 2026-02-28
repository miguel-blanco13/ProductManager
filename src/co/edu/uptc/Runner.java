package co.edu.uptc;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.ProductModelManagerList;
import co.edu.uptc.presenter.ProductPresenter;
import co.edu.uptc.view.ConsoleView;

public class Runner {
    public void run() {
        ModelInterface model = new ProductModelManagerList();
        ViewInterface view = new ConsoleView();
        ProductPresenter presenter = new ProductPresenter(model, view);
        presenter.start();
    }
}