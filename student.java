import java.util.ArrayList;

public class Student extends Person {
    private double gpa;
    private String major;
    private ArrayList<String> courseList;

    public Student(String fullName, int idNumber, Address homeAddress,
                   double gpa, String major) {
        super(fullName, idNumber, homeAddress);
        setGpa(gpa);
        setMajor(major);
        this.courseList = new ArrayList<>();
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ArrayList<String> getCourseList() {
        return courseList;
    }

    public void addCourse(String courseName) {
        courseList.add(courseName);
    }

    public int getCourseCount() {
        return courseList.size();
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Major: " + major +
               ", GPA: " + gpa +
               ", Courses: " + getCourseCount();
    }
}
