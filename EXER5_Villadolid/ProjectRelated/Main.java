import AgriConnect.Farmers;
import AgriConnect.Marketplace;
import AgriConnect.Product;

public class Main {
    public static void main(String[] args) {
        // Create farmer
        Farmers farmer1 = new Farmers("Juan Dela Cruz", "Davao Oriental");
        farmer1.displayInfo();

        // Create products
        Product rice = new Product("Rice", 45.0);
        Product corn = new Product("Corn", 30.0);

        // Create marketplace
        Marketplace market = new Marketplace();
        market.addProduct(rice);
        market.addProduct(corn);

        // Show products in marketplace
        market.showProducts();
    }
}
