public class TransportationTester {
    public static void main(String[] args) {
        // Air Transports
        Transportation heli = new Helicopter(6);
        Transportation plane = new Airplane(180);
        Transportation shuttle = new SpaceShuttle(7);

        // Land Transports
        Transportation truck = new Truck(2);
        Transportation suv = new SUV(5);
        Transportation tricycle = new Tricycle(3);
        Transportation motorcycle = new Motorcycle(2);
        Transportation kariton = new Kariton(1);

        // Water Transports
        Transportation boat = new Boat(20);
        Transportation submarine = new Submarine(50);

        // Test all transports
        heli.travel();
        plane.travel();
        shuttle.travel();
        truck.travel();
        suv.travel();
        tricycle.travel();
        motorcycle.travel();
        kariton.travel();
        boat.travel();
        submarine.travel();
    }
}
