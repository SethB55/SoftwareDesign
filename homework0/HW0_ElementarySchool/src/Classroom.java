import java.util.ArrayList;
public class Classroom {
    private int gradeLevel;
    private Teacher teacher;
    private ArrayList<Student> students;

    public Classroom(Teacher t1, int gradeLevel) {
        this.teacher = teacher;
        this.gradeLevel = gradeLevel;
    }


    public void Classroom(Teacher teacher, int gradeLevel, ArrayList students) {
            this.teacher = teacher;
            this.gradeLevel = gradeLevel;
            this.students = students;
        }

    public void rollCall(){
        System.out.print("Roll Call: ");
        for(int i =0; i < students.size(); i++){
            System.out.print(students.get(i));
            System.out.print(students.get(i).getLunchPreference());
        }
    }

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
    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getID() {
        return teacher.getName().charAt(0) + String.valueOf(gradeLevel);
    }


    public void addStudent(Student student) {
        students.add(student);
    }

}

