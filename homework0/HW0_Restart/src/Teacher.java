/**
 * Class: Teacher
 * Extends Person and adds subject, degree, university, and unique ID functionality.
 */
public class Teacher extends Person {
    private String subject;
    private String degree;
    private String university;
    private static int idCounter = 1; /** Static counter to generate unique teacher IDs */
    private int id; /** Stores the teacher's ID */

    /** Default constructor assigns an ID but does not initialize other attributes */
    public Teacher(){
        this.id = idCounter;
        idCounter += 1;
    }

    /** Constructor initializing name, subject, degree, university, and assigning ID */
    public Teacher(String name, String subject, String degree, String university){
        super(name);
        this.subject = subject;
        this.degree = degree;
        this.university = university;
        this.id = idCounter;
        idCounter += 1;
    }

    /** Returns the teacher's subject */
    public String getSubject() {
        return subject;
    }

    /** Sets the teacher's subject */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /** Returns the teacher's degree */
    public String getDegree() {
        return degree;
    }

    /** Sets the teacher's degree */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /** Returns the teacher's university */
    public String getUniversity() {
        return university;
    }

    /** Sets the teacher's university */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**  Returns the teacher's unique ID */
    public int getId() {
        return id;
    }

    /** Returns the next available teacher ID */
    public static int getNextId(){
        return idCounter;
    }

    /** Returns a string representation of the teacher */
    @Override
    public String toString() {
        return "I am " + super.getName() + ", a teacher of "
                + university + ". I hold a " + degree + " degree from " +
                university + ", and my ID is " + id + ".";
    }
}
