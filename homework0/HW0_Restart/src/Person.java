/**
 * Class: Person
 * Represents a general person with a name.
 */
public class Person {
    private String name; // Stores the name of the person

    /** Default constructor initializing name to null */
    public Person(){
        this.name = null;
    }

    /** Constructor with name parameter */
    public Person(String name){
        this.name = name;
    }

    /** Sets the name of the person */
    public void setName(String name) {
        this.name = name;
    }

    /** Retrieves the name of the person */
    public String getName(){
        return name;
    }

    /** Returns a string representation of the person */
    @Override
    public String toString() {
        return "Your name is: " + name;
    }
}
