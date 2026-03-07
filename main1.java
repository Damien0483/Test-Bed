public class Main {
    public static void main(String[] args) {

        // Create an Address
        Address addr = new Address("123 Maple Street", "Springfield", "22150");

        // Create a Student
        Student stu = new Student("Alice Johnson", 10245, addr, 3.85, "Computer Science");

        // Add some courses
        stu.addCourse("Data Structures");
        stu.addCourse("Operating Systems");
        stu.addCourse("Discrete Mathematics");

        // Print the student (uses overridden toString)
        System.out.println(stu.toString());
    }
}
