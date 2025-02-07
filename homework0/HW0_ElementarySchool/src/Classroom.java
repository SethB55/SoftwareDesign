import java.util.ArrayList;
public class Classroom {
    private int gradeLevel;
    private Teacher teacher;
    private ArrayList<Student> students;
    public Classroom(){


    Classroom(Teacher teacher, int gradeLevel) {
            this.teacher = teacher;
            this.gradeLevel = gradeLevel;
            this.students = new ArrayList<>();
        }
    }
    public int rollCall(){
        return
    }
    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }


}
