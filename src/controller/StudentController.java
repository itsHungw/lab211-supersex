package controller;

import model.Student;
import service.MountainService;
import service.StudentService;
import utils.Acceptable;
import utils.Inputer;
import view.ConsoleView;

import java.util.List;

public class StudentController {
    private final MountainService mountainService;
    private final StudentService studentService;
    private final ConsoleView view;

    public StudentController(StudentService studentService, MountainService mountainService, ConsoleView view) {
        this.studentService = studentService;
        this.mountainService = mountainService;
        this.view = view;
    }

    public void addStudent() {
        String studentId; //Input ID and check valid
        // Loop until a unique student ID is entered
        while (true) {
            studentId = Inputer.getInputWithLoop("Enter student ID please: ", Acceptable.STUDENT_ID, "Input must be a valid student ID !", false);
            if (!studentService.isIdDuplicate(studentId)) {
                break;
            }
            System.out.println("Student ID already exists!");
        }
        //Student name
        String studentName = Inputer.getInputWithLoop("Enter student name please: ", Acceptable.NAME_VALID, "Name must be longer than 2 characters !", false);
        //Student phone number
        String studentPhone;
        // Loop until a unique phone number is entered
        while (true) {
            studentPhone = Inputer.getInputWithLoop("Enter student phone numbers please: ", Acceptable.PHONE_VALID, "Invalid phone number !", false);
            if (!studentService.isPhoneDuplicate(studentPhone)) {
                break;
            }
            System.out.println("Phone number already exists!");
        }
        //Student email
        String studentEmail;
        // Loop until a unique email is entered
        while (true) {
            studentEmail = Inputer.getInputWithLoop("Enter student email please: ", Acceptable.EMAIL_VALID, "Invalid email address !", false);
            if (!studentService.isEmailDuplicate(studentEmail)) {
                break;
            }
            System.out.println("Email already exists!");
        }
        //Mountain code
        String mountainCode;
        // Loop until a valid mountain code is entered
        do {
            view.displayMountains(mountainService.getAllMountains());
            mountainCode = Inputer.getString("Enter mountain ID code: ");
            if (mountainService.isValidMountainCode(mountainCode)) {
                break;
            }
            System.out.println("Invalid mountain code. Please try again.");
        } while (true);

        //Save
        // Ask for confirmation before saving
        while (true) {
            String choice = Inputer.getString("Do you want to save ? (Y/N) ");
            if (choice.equalsIgnoreCase("Y")) {
                studentService.addStudent(studentId, studentName, studentPhone, studentEmail, mountainCode);
                System.out.println("Student added successfully!");
                break;
            } else if (choice.equalsIgnoreCase("N")) {
                break;
            } else System.out.println("Invalid choice. Please choose Y or N !");
        }

    }

    public void findStudentById(){
        String studentId = Inputer.getInputWithLoop("Enter student ID please: ", Acceptable.STUDENT_ID, "Input must be a valid student ID !", false);
        Student student = studentService.findStudentById(studentId);
        if (student != null) {
            view.viewStudent(student);
        } else System.out.println("Student not found!");
    }

    public void updateStudent() {
        String studentId = Inputer.getInputWithLoop("Enter student ID please: ", Acceptable.STUDENT_ID, "Input must be a valid student ID !", false);
        updateOwnProfile(studentId);
    }

    public void updateOwnProfile(String studentId) {
        Student student = studentService.findStudentById(studentId);
        if (student != null) {
            System.out.println("Press Enter to keep old student details!");
            // Get new information, allow empty input to keep old data
            String name = Inputer.getInputWithLoop("Enter your new name: ", Acceptable.NAME_VALID, "Name must be longer than 2 characters !", true);
            String phone = Inputer.getInputWithLoop("Enter your new phone number: ", Acceptable.PHONE_VALID, "Invalid phone number !", true);
            String email = Inputer.getInputWithLoop("Enter your new email: ", Acceptable.EMAIL_VALID, "Invalid email address !", true);
            String codeMountain;
            do {
                view.displayMountains(mountainService.getAllMountains());
                codeMountain = Inputer.getString("Enter mountain ID code: ");
                if (mountainService.isValidMountainCode(codeMountain)) {
                    break;
                }
                if (codeMountain.isEmpty()) {
                    break;
                }
                System.out.println("Invalid mountain code. Please try again.");
            } while (true);

            studentService.updateStudent(student, name, phone, email, codeMountain);
            System.out.println("Student updated successfully!");
        } else
            System.out.println("This student has not registered yet !");
    }

    public void deleteStudent() {
        String studentId = Inputer.getInputWithLoop("Enter student ID please: ", Acceptable.STUDENT_ID, "Input must be a valid student ID !", false);
        Student student = studentService.findStudentById(studentId);
        if (student != null) {
            view.displayStudentDetails(student);
            // Ask for confirmation before deleting
            while (true) {
                String choice = Inputer.getString("Do you want to DELETE this student with ID " +student.getId()  +"? (Y/N) ");
                if (choice.equalsIgnoreCase("Y")) {
                    studentService.deleteStudent(student);
                    System.out.println("The registration has been successfully deleted.");
                    break;
                } else if (choice.equalsIgnoreCase("N")) {
                    break;
                } else System.out.println("Invalid choice. Please choose Y or N !");
            }
        } else System.out.println("This student has not registered yet.");
    }

    public void findStudentByName() {
        String nameStudentFind = Inputer.getString("Enter name of student want to find: ");
        List<Student> studentList = studentService.findStudentByName(nameStudentFind);
        if (!studentList.isEmpty()) {
            view.displayStudents(studentList);
        } else System.out.println("No one matches the search criteria!");
    }

    public void filterStudentByCampus() {
        view.displayCampus();
        String campusCode = Inputer.getString("Enter campus code: ");
        if (studentService.filterByCampusCode(campusCode).isEmpty()) {
            System.out.println("Wrong campus code or No students have registered under this campus.");
        } else
            view.displayStudents(studentService.filterByCampusCode(campusCode));
    }

    public void listStudents() {
        if(!studentService.getAllStudents().isEmpty()) {
            view.displayStudents(studentService.getAllStudents());
        }else System.out.println("No students have registered yet.");
    }
}
