/**
 * Class: Student
 * Extends Person and adds lunch preference functionality.
 */
public class Student extends Person{
    private String lunchPreference; // Stores the student's lunch preference */

    /** Constructor initializing name and lunch preference */
    public Student(String name, String lunchPreference){
        super(name);
        this.lunchPreference = lunchPreference;
    }

    /** Returns the student's lunch preference */
    public String getLunchPreference() {
        return lunchPreference;
    }

    /** Sets the student's lunch preference */
    public void setLunchPreference(String lunchPreference) {
        this.lunchPreference = lunchPreference;
    }

    /** Returns a string representation of the student */
    @Override
    public String toString() {
        return "I am " + super.getName() + ", who prefers a " + lunchPreference + " lunch.";
    }
}