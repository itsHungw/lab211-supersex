package view;

import model.Mountain;
import model.Statistic;
import model.Student;

import java.util.Collection;
import java.util.List;

public class ConsoleView {

    public void printStatistics(List<Statistic> statistics) {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("| %-14s| %-29s| %-19s| %-19s|\n", "Mountain Code", "Mountain Name", "Participants", "Total Tuition (VND)");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Statistic s : statistics) {
            System.out.println(s);
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public void viewStudent(Student student) {
        System.out.printf("%-122s\n",
                "+---------------+-------------------------+---------------+-------------------------+--------------------+-----------------+");
        System.out.printf("| %-14s| %-24s| %-14s| %-24s| %-19s| %-16s|\n",
                "Student ID", "Student Name", "Phone numbers", "Email", "Mountain code", "Tution fee(VND)");
        System.out.printf("%-122s\n",
                "+---------------+-------------------------+---------------+-------------------------+--------------------+-----------------+");
        System.out.println(student);
        System.out.printf("%-122s\n",
                "+---------------+-------------------------+---------------+-------------------------+--------------------+-----------------+");
    }

    public void displayStudentDetails(Student student) {
        System.out.println("------------------Student Details-------------------");
        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Phone: " + student.getPhone());
        System.out.println("Mountain: " + student.getMountainCode());
        System.out.println("Fee: " + student.getTutionFee() + " VND");
        System.out.println("-----------------------------------------------------");
    }

    public void displayStudents(List<Student> students) {
        System.out.printf("%-122s\n",
                "+---------------+-------------------------+---------------+-------------------------+--------------------+-----------------+");
        System.out.printf("| %-14s| %-24s| %-14s| %-24s| %-19s| %-16s|\n",
                "Student ID", "Student Name", "Phone numbers", "Email", "Mountain code", "Tution fee(VND)");
        System.out.printf("%-122s\n",
                "+---------------+-------------------------+---------------+-------------------------+--------------------+-----------------+");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.printf("%-122s\n",
                "+---------------+-------------------------+---------------+-------------------------+--------------------+-----------------+");
    }

    public void displayCampus() {
        System.out.println("Campus and campus code:");
        System.out.println("1. SE: Ho Chi Minh");
        System.out.println("2. HE: Ha Noi");
        System.out.println("3. DE: Da Nang");
        System.out.println("4. QE: Quang Ngai");
        System.out.println("5. CE: Can Tho");
    }

    public void displayMountains(Collection<Mountain> mountains) {
        String lineSeparator = String.format("%-228s", "").replace(' ', '-');
        System.out.println(lineSeparator);
        System.out.printf("| %-5s| %-24s| %-19s| %-170s |\n", "CODE", "MOUNTAIN", "PROVINCE", "DESCRIPTION");
        System.out.println(lineSeparator);
        for (Mountain m : mountains) {
            System.out.println(m);
        }
        System.out.println(lineSeparator);
    }

    public void displayMenu() {
        System.out.println("\n---------- MOUNTAIN HIKING MANAGEMENT ----------");
        System.out.println("1. Add a new Student");
        System.out.println("2. Display all Students");
        System.out.println("3. Display all Mountains");
        System.out.println("4. Find Student by ID");
        System.out.println("5. Update Student by ID");
        System.out.println("6. Delete Student by ID");
        System.out.println("7. Find Student by name");
        System.out.println("8. Filter student by Campus");
        System.out.println("9. Show Statistics");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayAdminMenu() {
        System.out.println("\n---------- ADMIN MENU ----------");
        System.out.println("1. Add a new Student");
        System.out.println("2. Display all Students");
        System.out.println("3. Display all Mountains");
        System.out.println("4. Find Student by ID");
        System.out.println("5. Update Student by ID");
        System.out.println("6. Delete Student by ID");
        System.out.println("7. Find Student by name");
        System.out.println("8. Filter student by Campus");
        System.out.println("9. Show Statistics");
        System.out.println("10. Logout");
        System.out.print("Enter your choice: ");
    }

    public void displayUserMenu(String username) {
        System.out.println("\n---------- STUDENT MENU (" + username + ") ----------");
        System.out.println("1. View My Information");
        System.out.println("2. Register Hiking");
        System.out.println("3. Update My Information");
        System.out.println("4. View Mountain List");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }

    public void registerRequired() {
        System.out.println("----------REGISTER ACCOUNT----------");
        displayCampus();
        System.out.println("ID Format: Campus code + 6 number");
        System.out.println("ID Example: SE123456");
    }

    public void programHeader() {
        System.out.println("Welcome to the Mountain Hiking Challenge Registration System!");
        System.out.println("1. Register");
        System.out.println("2. Login");
    }
}
