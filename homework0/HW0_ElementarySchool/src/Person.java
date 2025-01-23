public class Person {
    private String name; //initializing attribute
    public Person(){
        this.name = null;
    }
    public Person(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        return "Your name is: " + name;
    }
}
