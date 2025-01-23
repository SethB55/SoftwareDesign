public class Person {
    private String name; //initializing attribute
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        System.out.println("Your name is: " + name);
        return super.toString();
    }
}
