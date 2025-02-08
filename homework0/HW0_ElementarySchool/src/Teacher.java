public class Teacher extends Person {
    public Teacher(){
        this.id = idCounter;
        idCounter++;
    }
    public Teacher(String name, String subject, String degree, String university){
        super(name);
        this.subject = subject;
        this.degree = degree;
        this.university = university;
    }
    private String subject;
    private String degree;
    private String university;
    private static int idCounter = 1;
    private int id;
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "I am " + super.getName() + ", a teacher of "
                + university + ". I hold a " + degree + " degree from " +
                university + ", and my ID is " + id + ".";
    }
}
