import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Full Name: ");
        String fullName = input.nextLine();

        System.out.print("Enter ID Number: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Enter Major: ");
        String major = input.nextLine();

        System.out.print("Enter GPA: ");
        double gpa = Double.parseDouble(input.nextLine());

        System.out.print("Enter Street: ");
        String street = input.nextLine();

        System.out.print("Enter City: ");
        String city = input.nextLine();

        System.out.print("Enter Zip: ");
        String zip = input.nextLine();

        Address addr = new Address(street, city, zip);
        Student stu = new Student(fullName, id, addr, gpa, major);

        // Collect 3 courses
        for (int i = 1; i <= 3; i++) {
            System.out.print("\nEnter course #" + i + ": ");
            String course = input.nextLine();
            stu.addCourse(course);
        }

        // Update major
        System.out.println("\nUpdating Major to Cybersecurity...");
        stu.setMajor("Cybersecurity");

        // Final Output
        System.out.println("\n--- Final Student Profile ---");
        System.out.println(stu.toString());

        System.out.println("\nIndividual Course List:");
        for (String c : stu.getCourseList()) {
            System.out.println(" >> " + c);
        }

        input.close();
    }
}
