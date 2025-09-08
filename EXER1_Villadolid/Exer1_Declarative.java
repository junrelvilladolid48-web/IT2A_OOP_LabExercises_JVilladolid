import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exer1_Declarative {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Declarative: we describe *what* we want, not *how*
        List<Integer> result = numbers.stream()
                                      .filter(n -> n % 2 == 0)
                                      .map(n -> n * n)
                                      .collect(Collectors.toList());

        System.out.println("Even numbers squared: " + result);
    }
}
