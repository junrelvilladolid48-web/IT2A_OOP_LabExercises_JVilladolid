package AgriConnect;

import java.util.ArrayList;
import java.util.List;

public class Marketplace {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " added to marketplace.");
    }

    public void showProducts() {
        System.out.println("\n--- Marketplace Products ---");
        for (Product p : products) {
            p.displayInfo();
        }
    }
}
