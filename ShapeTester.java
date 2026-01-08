public class ShapeTester {

    public static void main(String[] args) {

        Shape circle = new Circle(5);
        Shape square = new Square(4);
        Shape rectangle = new Rectangle(6, 3);
        Shape triangle = new Triangle(3, 4, 5);

        System.out.println("Circle:");
        circle.displayInfo();

        System.out.println("\nSquare:");
        square.displayInfo();

        System.out.println("\nRectangle:");
        rectangle.displayInfo();

        System.out.println("\nTriangle:");
        triangle.displayInfo();
    }
}
