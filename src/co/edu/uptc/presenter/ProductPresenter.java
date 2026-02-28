package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.Product;

import java.util.List;

public class ProductPresenter implements PresenterInterface {
    private ModelInterface model;
    private ViewInterface view;

    public ProductPresenter(ModelInterface model, ViewInterface view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        boolean running = true;
        while (running) {
            this.view.clearScreen();
            this.view.showMenu();
            String option = this.view.getInput("Selecciona una opción: ");
            running = handleOption(option.trim());
        }
    }

    private boolean handleOption(String option) {
        switch (option) {
            case "1": this.addProduct();        return true;
            case "2": this.listProducts();      return true;
            case "3": this.listProductsSorted(); return true;
            case "4": this.deleteProducts();    return true;
            case "5": this.view.showMessage("¡Hasta luego!"); return false;
            default:  this.view.showError("Opción no válida. Intenta de nuevo."); return true;
        }
    }

    public void addProduct() {
        this.view.clearScreen();
        this.view.showMessage("=== AGREGAR PRODUCTO ===");
        String description = readDescription();
        if (description == null) return;
        Double price = readPrice();
        if (price == null) return;
        String unit = readUnit();
        if (unit == null) return;
        this.model.addProduct(new Product(description, price, unit));
        confirmProductAdded();
    }

    private void confirmProductAdded() {
        this.view.showMessage("✔ Producto agregado correctamente.");
        this.view.getInput("\nPresiona Enter para continuar...");
    }

    private String readDescription() {
        String description = this.view.getInput("Descripción: ");
        if (description.isBlank()) {
            this.view.showError("La descripción no puede estar vacía.");
            return null;
        }
        return description;
    }

    private Double readPrice() {
        try {
            double price = Double.parseDouble(this.view.getInput("Precio: "));
            if (price < 0.0) throw new NumberFormatException();
            return price;
        } catch (NumberFormatException e) {
            this.view.showError("Precio no válido. Debe ser un número positivo.");
            return null;
        }
    }

    private String readUnit() {
        String unit = this.view.getInput("Unidad de medida: ");
        if (unit.isBlank()) {
            this.view.showError("La unidad de medida no puede estar vacía.");
            return null;
        }
        return unit;
    }

    public void listProducts() {
        this.view.clearScreen();
        this.view.showMessage("=== LISTA DE PRODUCTOS ===");
        if (this.model.isEmpty()) {
            this.view.showError("No hay productos registrados.");
        } else {
            List<Product> products = this.model.getAllProducts();
            this.view.showProducts(products);
            this.view.showMessage("\nTotal: " + this.model.getProductCount() + " producto(s).");
        }

        this.view.getInput("\nPresiona Enter para continuar...");
    }

    public void listProductsSorted() {
        this.view.clearScreen();
        this.view.showMessage("=== PRODUCTOS ORDENADOS POR NOMBRE ===");
        if (this.model.isEmpty()) {
            this.view.showError("No hay productos registrados.");
        } else {
            List<Product> sortedProducts = this.model.getProductsSortedByName();
            this.view.showProducts(sortedProducts);
            this.view.showMessage("\nTotal: " + this.model.getProductCount() + " producto(s).");
        }

        this.view.getInput("\nPresiona Enter para continuar...");
    }

    public void deleteProducts() {
        this.view.clearScreen();
        this.view.showMessage("=== BORRAR PRODUCTOS ===");
        if (this.model.isEmpty()) {
            this.view.showError("No hay productos registrados.");
            this.view.getInput("\nPresiona Enter para continuar...");
            return;
        }
        String searchTerm = this.view.getInput("Ingresa el nombre o parte del nombre a eliminar: ");
        if (searchTerm.isBlank()) {
            this.view.showError("Debes ingresar un término de búsqueda.");
        } else {
            showDeleteResult(this.model.deleteProductsByName(searchTerm));
        }
        this.view.getInput("\nPresiona Enter para continuar...");
    }

    private void showDeleteResult(int deletedCount) {
        if (deletedCount == 0) {
            this.view.showError("No se encontraron productos con ese nombre.");
        } else {
            this.view.showMessage("✔ Se eliminaron " + deletedCount + " producto(s).");
        }
    }
    
}