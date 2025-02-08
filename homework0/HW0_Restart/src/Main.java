/**
 * Class: Main
 * Entry point of the program that initializes and demonstrates the classes.
 */
public class Main {

    public static void main(String[] args) {
        // Creating teachers with subject, degree, and university
        Teacher t1 = new Teacher("Mr. Smith", "Mathematics", "MSc", "Harvard University");
        Teacher t2 = new Teacher("Ms. Johnson", "English", "MA", "Stanford University");

        // Creating students with name and lunch preference
        Student s1 = new Student("Alice", "Hot");
        Student s2 = new Student("Bob", "Cold");
        Student s3 = new Student("Charlie", "Hot");
        Student s4 = new Student("David", "Cold");
        Student s5 = new Student("Eve", "Hot");
        Student s6 = new Student("Frank", "Cold");

        // Creating student arrays for classrooms
        Student[] classA = {s1, s2, s3};
        Student[] classB = {s4, s5, s6};

        // Creating classrooms
        Classroom class1 = new Classroom(t1, 5, classA);
        Classroom class2 = new Classroom(t2, 6, classB);

        // Printing class details and performing roll calls
        System.out.println(class1);
        class1.rollCall();
        System.out.println(class2);
        class2.rollCall();

        System.out.println("");

        // Displaying the next available teacher ID
        System.out.print("The next teacher ID will be: ");
        System.out.println(Teacher.getNextId());
    }
}
