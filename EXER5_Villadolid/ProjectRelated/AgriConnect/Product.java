package AgriConnect;

public class Product {
    private String name;
    private double pricePerKg;

    public Product(String name, double pricePerKg) {
        this.name = name;
        this.pricePerKg = pricePerKg;
    }

    public String getName() {
        return name;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void displayInfo() {
        System.out.println("Product: " + name + " | Price per Kg: Php " + pricePerKg);
    }
}
