public class Cat {
    private String name;
    private String breed;
    private String age;
    private String color;

    public Cat(String name, String breed, String age, String color) {
        this.name =name;
        this.breed = breed;
        this.age = age;
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getAge () {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String  color) {
        this.color = color;
    }
    public String displayTag() {
        String tag = "Name: " + this.getName() + "\nBreed: " + this.getBreed() + "\nAge: " + this.getAge() + "\nColor: " + this.getColor();
        return tag;
    }
}