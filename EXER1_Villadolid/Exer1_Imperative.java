import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exer1_Imperative {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = new ArrayList<>();

        // Imperative style: explicit step-by-step instructions
        for (int n : numbers) {
            if (n % 2 == 0) {
                result.add(n * n);
            }
        }

        System.out.println("Even numbers squared: " + result);
    }
}
