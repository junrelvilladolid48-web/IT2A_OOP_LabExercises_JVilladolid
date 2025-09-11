public class Car {
    
    private String brand;
    private String model;
    private String year;
    private String bodyType;
    private String engine;
    private String transmssion;
    private String driveType;
    private String horsepower;
    private String torque;
    private String fuelCapacity;
    private String mileage;
    private String seatingCapacity;
    private String dimensions;
    private String safetyFeatures;
    private String entertainment;

    public Car() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = "Unknown";
        this.bodyType = "Unknown";
        this.engine = "Unknown";
        this.transmssion = "Unknown";
        this.driveType = "Unknown";
        this.horsepower = "Unknown";
        this.torque = "Unknwon";
        this.fuelCapacity = "Unknwon";
        this.mileage = "Unknown";
        this.seatingCapacity = "Unknown";
        this.dimensions = "Unknown";
        this.safetyFeatures = "Unknown";
        this.entertainment = "Unknown";
    }
    public Car(String brand, String model, String year, String bodyType, String engine, String fuelType, String transmission, String driveType, String horsepower, String torque, String fuelCapacity, String mileage, String seatingCapacity, String dimensions, String safetyFeatures, String entertainment) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.bodyType = bodyType;
        this.engine = engine;
        this.transmssion = transmission;
        this.driveType = driveType;
        this.horsepower = horsepower;
        this.torque = torque;
        this.fuelCapacity = fuelCapacity;
        this.mileage = mileage;
        this.seatingCapacity = seatingCapacity;
        this.dimensions = dimensions;
        this.safetyFeatures = safetyFeatures;
        this.entertainment = entertainment;
    } 
    public void displayInfo() {
        String info = "";
        info += "Brand: " + this.brand;
        info += "\nModel: " + this.model;
        info += "\nYear:" + this.year;
        info += "\nBody Type: " + this.bodyType;
        info += "\nEngine: " + this.engine;
        info += "\nFuel Type: " + this.fuelCapacity;
        info += "\nTransmission: " + this.transmssion;
        info += "\nDrive Type: " + this.driveType;
        info += "\nHorsepower: " + this.horsepower;
        info += "\nTorque: " + this.torque;
        info += "\nFuel Capacity: " + this.fuelCapacity;
        info += "\nMileage: " + this.mileage;
        info += "\nSeating Capacity: " + this.seatingCapacity;
        info += "\nDimensions: " + this.dimensions;
        info += "\nSafety Features: " + this.safetyFeatures;
        info += "\nEntertainment: " + this.entertainment;
        System.out.println(info);
    }
}
