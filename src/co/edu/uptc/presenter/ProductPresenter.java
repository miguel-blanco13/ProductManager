package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.Product;

import java.util.List;

public class ProductPresenter implements PresenterInterface {
    private ModelInterface model;
    private ViewInterface view;

    public ProductPresenter(ModelInterface var1, ViewInterface var2) {
        this.model = var1;
        this.view = var2;
    }

    public void start() {
        boolean var1 = true;

        while(var1) {
            this.view.clearScreen();
            this.view.showMenu();
            String var2 = this.view.getInput("Selecciona una opción: ");
            switch (var2.trim()) {
                case "1":
                    this.addProduct();
                    break;
                case "2":
                    this.listProducts();
                    break;
                case "3":
                    this.listProductsSorted();
                    break;
                case "4":
                    this.deleteProducts();
                    break;
                case "5":
                    this.view.showMessage("¡Hasta luego!");
                    var1 = false;
                    break;
                default:
                    this.view.showError("Opción no válida. Intenta de nuevo.");
            }
        }

    }

    public void addProduct() {
        this.view.clearScreen();
        this.view.showMessage("=== AGREGAR PRODUCTO ===");
        String var1 = this.view.getInput("Descripción: ");
        if (var1.isBlank()) {
            this.view.showError("La descripción no puede estar vacía.");
        } else {
            String var2 = this.view.getInput("Precio: ");

            double var3;
            try {
                var3 = Double.parseDouble(var2);
                if (var3 < 0.0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException var6) {
                this.view.showError("Precio no válido. Debe ser un número positivo.");
                return;
            }

            String var5 = this.view.getInput("Unidad de medida: ");
            if (var5.isBlank()) {
                this.view.showError("La unidad de medida no puede estar vacía.");
            } else {
                this.model.addProduct(new Product(var1, var3, var5));
                this.view.showMessage("✔ Producto agregado correctamente.");
                this.view.getInput("\nPresiona Enter para continuar...");
            }
        }
    }

    public void listProducts() {
        this.view.clearScreen();
        this.view.showMessage("=== LISTA DE PRODUCTOS ===");
        if (this.model.isEmpty()) {
            this.view.showError("No hay productos registrados.");
        } else {
            List var1 = this.model.getAllProducts();
            this.view.showProducts(var1);
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
            List var1 = this.model.getProductsSortedByName();
            this.view.showProducts(var1);
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
        } else {
            String var1 = this.view.getInput("Ingresa el nombre o parte del nombre a eliminar: ");
            if (var1.isBlank()) {
                this.view.showError("Debes ingresar un término de búsqueda.");
                this.view.getInput("\nPresiona Enter para continuar...");
            } else {
                int var2 = this.model.deleteProductsByName(var1);
                if (var2 == 0) {
                    this.view.showError("No se encontraron productos con ese nombre.");
                } else {
                    this.view.showMessage("✔ Se eliminaron " + var2 + " producto(s).");
                }

                this.view.getInput("\nPresiona Enter para continuar...");
            }
        }
    }
    
}