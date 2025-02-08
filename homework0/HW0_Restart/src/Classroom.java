/*
 * Class: Classroom
 * Represents a classroom with a teacher, grade level, and students.
 */
public class Classroom {
    private int gradeLevel; /** Stores the grade level of the class */
    private Teacher teacher; /** Stores the teacher assigned to the class */
    private Student[] students; /** Stores the list of students in the class */

    /** Constructor initializing teacher, grade level, and student array */
    public Classroom(Teacher teacher, int gradeLevel, Student[] students) {
        this.teacher = teacher;
        this.gradeLevel = gradeLevel;
        this.students = students;
    }

    /** Performs a roll call by printing out each student's details */
    public void rollCall() {
        System.out.print("Roll Call: ");
        for (int i = 0; i < students.length; i++) {
            System.out.print(students[i].toString() + " ");
        }
    }

    /** Generates a lunch report counting hot and cold  */
    public String generateLunchReport() {
        int hotCount = 0, coldCount = 0;
        for (Student student : students) {
            if (student.getLunchPreference().equalsIgnoreCase("Hot")) {
                hotCount++;
            } else {
                coldCount++;
            }
        }
        return "Hot lunch: " + hotCount + ", Cold lunch: " + coldCount;
    }

    /** Returns the grade level of the class */
    public int getGradeLevel() {
        return gradeLevel;
    }

    /** Sets the grade level of the class */
    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    /** Returns a string representation of the classroom */
    @Override
    public String toString(){
        System.out.println(" ");
        return "I am " + teacher.getName() + " , a teacher of " + teacher.getSubject() +". I hold a "
                + teacher.getDegree() + " degree from " + teacher.getUniversity() + ", and my ID is " + teacher.getId() + ".";

    }
}