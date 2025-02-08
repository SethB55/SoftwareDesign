public class main(String[] args) {
    Teacher t1 = new Teacher("Mr. Smith", "Mathematics", "MSc", "Harvard University");
    Teacher t2 = new Teacher("Ms. Johnson", "English", "MA", "Stanford University");

    Student s1 = new Student("Alice", "Hot");
    Student s2 = new Student("Bob", "Cold");
    Student s3 = new Student("Charlie", "Hot");
    Student s4 = new Student("David", "Cold");
    Student s5 = new Student("Eve", "Hot");
    Student s6 = new Student("Frank", "Cold");
    Student [] classA = {s1, s2, s3};
    Student [] classB = {s4, s5, s6};
    Classroom class1 = new Classroom(t1, 5, classA);

    Classroom class2 = new Classroom(t2, 6, classB);

    // Print classroom details
    System.out.println("Roll Call:");
    //System.out.println(class1.rollCall());
    System.out.println("Lunch Report: "+class1.generateLunchReport());

    System.out.println();

    System.out.println(class2);
    System.out.println("Roll Call:");
    //System.out.println(class2.rollCall());
    System.out.println("Lunch Report: "+class2.generateLunchReport());

    // Print next teacher ID
    System.out.println("Next new Teacher's ID would be: "+Teacher.nextID);
}
