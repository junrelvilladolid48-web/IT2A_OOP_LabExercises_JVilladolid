public class TransportationTester {
    public static void main(String[] args) {
        System.out.println("--- Testing Air Transport ---");
        Helicopter helicopter = new Helicopter();
        helicopter.takeOff();
        helicopter.land();
        helicopter.move();
        helicopter.stop();
        System.out.println();

        Airplane airplane = new Airplane();
        airplane.takeOff();
        airplane.land();
        System.out.println();

        SpaceShuttle spaceShuttle = new SpaceShuttle("ISS Resupply");
        spaceShuttle.launch();
        spaceShuttle.move();
        spaceShuttle.stop();
        System.out.println();

        System.out.println("--- Testing Land Transport ---");
        Truck truck = new Truck();
        truck.drive();
        truck.park();
        System.out.println();

        SUV suv = new SUV();
        suv.drive();
        suv.park();
        System.out.println();

        Tricycle tricycle = new Tricycle();
        tricycle.drive();
        System.out.println();

        Motorcycle motorcycle = new Motorcycle();
        motorcycle.move();
        motorcycle.stop();
        System.out.println();
        
        Kariton kariton = new Kariton();
        kariton.push();
        kariton.move();
        System.out.println();

        System.out.println("--- Testing Water Transport ---");
        Ship ship = new Ship();
        ship.sail();
        ship.dock();
        System.out.println();
        
        Submarine submarine = new Submarine();
        submarine.dive();
        submarine.sail();
        submarine.dock();
        System.out.println();
    }
}