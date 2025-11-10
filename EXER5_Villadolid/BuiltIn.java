import java.util.ArrayList; // for dynamic lists
import java.util.Scanner; // for user input
import java.util.Collections; // for sorting
import java.time.LocalDate; // for date handling
import java.lang.Math; // for mathematical functions

public class BuiltIn {
    public static void main(String[] args) {
        // 1. Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        // 2. ArrayList to store products
        ArrayList<String> products = new ArrayList<>();
        products.add("Rice");
        products.add("Corn");
        products.add("Banana");

        // 3. Collections to sort the list
        Collections.sort(products);

        // 4. LocalDate to show today’s date
        LocalDate today = LocalDate.now();

        // 5. Math for a calculation
        double price = 45.0;
        double discount = Math.round(price * 0.9); // 10% discount

        // Output results
        System.out.println("\nHello, " + name + "!");
        System.out.println("Today's date: " + today);
        System.out.println("Available products (sorted): " + products);
        System.out.println("Discounted price of Rice: ₱" + discount);

        sc.close();
    }
}