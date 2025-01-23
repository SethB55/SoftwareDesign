public class Person {
    private static String name; //initializing attribute
    public void setName(String name) {
        this.name = name;
    }
    public static String getName(){
        return name;
    }
    @Override
    public String toString() {
        return "Your name is: " + name;
    }
}
