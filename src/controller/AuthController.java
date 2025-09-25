package controller;

import service.AuthService;
import service.MountainService;
import service.StudentService;
import utils.Acceptable;
import utils.Inputer;
import view.ConsoleView;

public class AuthController {
    private final MountainService mountainService;
    private final StudentService studentService;
    private final AuthService authService;
    private final ConsoleView view;
    private boolean isSaved;

    public AuthController(StudentService studentService, MountainService mountainService, ConsoleView view, AuthService authService) {
        this.studentService = studentService;
        this.mountainService = mountainService;
        this.authService = authService;
        this.view = view;
    }

    public void addStudentWithRoleUser() {

        String studentId;
        studentId = authService.getCurrentUser().getStudentId();    //get own user ID

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
                authService.addStudentRoleUser(studentService, studentId, studentName, studentPhone, studentEmail, mountainCode);
                studentService.saveToFile();
                System.out.println("Student added successfully!");
                break;
            } else if (choice.equalsIgnoreCase("N")) {
                break;
            } else System.out.println("Invalid choice. Please choose Y or N !");
        }
    }
}
