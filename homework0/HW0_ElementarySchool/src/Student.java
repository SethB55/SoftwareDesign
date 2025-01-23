public class Student extends Person{
    private String lunchPreference;
    public String getLunchPreference() {
        return lunchPreference;
    }

    public void setLunchPreference(String lunchPreference) {
        this.lunchPreference = lunchPreference;
    }

    @Override
    public String toString() {
        return "I am " + Person.getName() + ", who prefers a " + lunchPreference + " lunch.";
    }
}
