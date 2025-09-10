public class CarTester {
    public static void main(String[] args) {
        Car c1 = new Car("Toyota", "Camry", "2020", "Sedan", "2.5L 4-Cylinder", "Gasoline", "8-Speed Automatic", "FWD" , "203 hp", "184 Lb-ft", "15.8 gal", "28/39 mpg", "5", "192.1\" L x 72.4\" W x 56.9\" H", "Toyota Safety Sense", "Apple CarPLay, Android Auto");
         Car c2 = new Car();
        c1.displayInfo();
        c2.displayInfo();

    }
    
}
