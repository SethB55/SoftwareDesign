public class Teacher extends Person {
    private String subject;
    private String degree;
    private String university;
    private int id =+ 1;
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
        System.out.println("I am " + Person.getName() + ", a teacher of "
                + university + ". I hold a " + degree + " degree from " +
                university + ", and my ID is " + id + ".");
        return super.toString();
    }
}
