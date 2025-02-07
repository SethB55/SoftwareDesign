public class Student extends Person{
    private String lunchPreference;
    public Student(String lunchPreference, String name){
        super(name);
        this.lunchPreference = lunchPreference;
    }
    public String getLunchPreference() {
        return lunchPreference;
    }

    public void setLunchPreference(String lunchPreference) {
        this.lunchPreference = lunchPreference;
    }

    @Override
    public String toString() {
        return "I am " + super.getName() + ", who prefers a " + lunchPreference + " lunch.";
    }
}
