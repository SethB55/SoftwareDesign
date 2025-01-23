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
        System.out.println("Your name is: " + name);
        return super.toString();
    }
}
