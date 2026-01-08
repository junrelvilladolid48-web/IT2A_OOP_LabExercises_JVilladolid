public abstract class Shape {

    public abstract double getArea();

    public abstract double getPerimeter();

    public void displayInfo() {
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}
