package co.edu.uptc.view;

import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.Product;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements ViewInterface {

    private final Scanner scanner;
    private static final int WIDTH = 55;

    private static final String RESET   = "\033[0m";
    private static final String CYAN    = "\033[36m";
    private static final String YELLOW  = "\033[33m";
    private static final String GREEN   = "\033[32m";
    private static final String RED     = "\033[31m";
    private static final String WHITE   = "\033[97m";
    private static final String BOLD    = "\033[1m";

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }


    private String repeat(String character, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) sb.append(character);
        return sb.toString();
    }

    private void printHorizontalBorder() {
        System.out.println(CYAN + "+" + repeat("-", WIDTH) + "+" + RESET);
    }

    private void printSeparator() {
        System.out.println(CYAN + "|" + repeat("-", WIDTH) + "|" + RESET);
    }

    private void printLine(String text) {
        int padding = WIDTH - text.length();
        int paddingLeft = padding / 2;
        int paddingRight = padding - paddingLeft;
        System.out.println(CYAN + "|" + RESET
                + repeat(" ", paddingLeft) + WHITE + BOLD + text + RESET
                + repeat(" ", paddingRight) + CYAN + "|" + RESET);
    }

    private void printLeftLine(String label, String text) {
        int visibleLength = 2 + label.length() + text.length();
        int padding = WIDTH - visibleLength;
        if (padding < 0) padding = 0;
        System.out.println(CYAN + "|" + RESET
                + "  " + YELLOW + label + RESET + WHITE + text + RESET
                + repeat(" ", padding) + CYAN + "|" + RESET);
    }

    private void printLeftLine(String text) {
        String content = "  " + text;
        int padding = WIDTH - content.length();
        if (padding < 0) padding = 0;
        System.out.println(CYAN + "|" + RESET
                + WHITE + content + RESET
                + repeat(" ", padding) + CYAN + "|" + RESET);
    }

    private void printEmptyLine() {
        System.out.println(CYAN + "|" + repeat(" ", WIDTH) + "|" + RESET);
    }

    private void printCenteredLine(String text, String color) {
        int padding = WIDTH - text.length();
        int paddingLeft = Math.max(0, padding / 2);
        int paddingRight = Math.max(0, padding - paddingLeft);
        System.out.println(CYAN + "|" + RESET
                + repeat(" ", paddingLeft) + color + BOLD + text + RESET
                + repeat(" ", paddingRight) + CYAN + "|" + RESET);
    }

    @Override
    public void showMenu() {
        printHorizontalBorder();
        printLine("ADMINISTRADOR DE PRODUCTOS - UPTC");
        printSeparator();
        printEmptyLine();
        printLeftLine("1.  ", "Adicionar producto");
        printLeftLine("2.  ", "Listar productos");
        printLeftLine("3.  ", "Mostrar productos ordenados por nombre");
        printLeftLine("4.  ", "Borrar productos");
        printLeftLine("5.  ", "Salir");
        printEmptyLine();
        printSeparator();
    }

    @Override
    public void showProducts(List<Product> products) {
        printHorizontalBorder();
        printLine("PRODUCTOS REGISTRADOS");
        printTableHeader();
        for (int i = 0; i < products.size(); i++) {
            printProductRow(i + 1, products.get(i));
        }
        printHorizontalBorder();
    }

    private void printTableHeader() {
        printSeparator();
        printLeftLine(String.format("%-25s %-10s %-12s", "DESCRIPCION", "PRECIO", "UNIDAD"));
        printSeparator();
    }

    private void printProductRow(int index, Product p) {
        String row = String.format("%-3d %-22s $%-9.2f %-12s",
                index,
                truncate(p.getDescription(), 22),
                p.getPrice(),
                p.getUnitOfMeasure());
        printLeftLine(row);
    }

    @Override
    public void showMessage(String message) {
        printHorizontalBorder();
        printCenteredLine(message, GREEN);
        printHorizontalBorder();
    }

    @Override
    public void showError(String error) {
        printHorizontalBorder();
        printCenteredLine("! " + error + " !", RED);
        printHorizontalBorder();
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(CYAN + "| " + RESET + YELLOW + prompt + RESET);
        String input = scanner.nextLine();
        printHorizontalBorder();
        return input;
    }

    @Override
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }
}