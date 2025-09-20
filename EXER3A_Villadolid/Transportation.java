public class Transportation {
    String name;
    int capacity;

    public Transportation(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void travel() {
        System.out.println(name + " is used for transportation.");
    }
}
