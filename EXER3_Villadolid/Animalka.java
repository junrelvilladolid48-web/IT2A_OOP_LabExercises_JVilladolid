public class Animalka {
    public static void main(String[] args) {
        Cat cat1 = new Cat ("Whiskers", "Siamese", "2 years", "Cream");
        System.out.println(cat1.displayTag());
        Cat cat2 = new Cat ("Mittens", "Maine C", null, null);
        System.out.println(cat2);
    }
}
