package controller;

import model.Student;
import service.AuthService;
import service.MountainService;
import service.StudentService;
import utils.Acceptable;
import utils.Inputer;
import view.ConsoleView;

public class MainController {
    private final AuthService authService;
    private final StudentService studentService;
    private final MountainService mountainService;
    private final ConsoleView view;
    private final StudentController studentController;
    private final MountainController mountainController;
    private final AuthController authController;
    private final StatisticController statisticController;

    public MainController(AuthService authService, StudentService studentService, MountainService mountainService, ConsoleView view) {
        this.authService = authService;
        this.studentService = studentService;
        this.mountainService = mountainService;
        this.view = view;
        this.studentController = new StudentController(studentService, mountainService, view);
        this.mountainController = new MountainController(mountainService, view);
        this.statisticController = new StatisticController();
        this.authController = new AuthController(studentService, mountainService, view, authService);
    }

    /**
     * Main application loop. Handles login and directs to the appropriate menu.
     */
    public void run() {
        studentService.LoadFromFile();

        while (true) {
            view.programHeader();
            String choice = Inputer.getString("Please choose your action: ");

            switch (choice) {
                case "1":
                    registerAccount();
                    break;

                case "2":
                    loginAccount();
                    // menu con sẽ tự xử lý logout
                    if (authService.isAdmin()) {
                        runAdminMenu();
                    } else {
                        runUserMenu();
                    }
                    // logout -> thoát hẳn chương trình
                    exitProgram();
                    return;

                case "exit":
                    exitProgram();
                    return;

                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }
    }

    // gom save + exit message vào 1 hàm
    private void exitProgram() {
        studentService.saveToFile();
        System.out.println("Exiting program. All changes have been saved. Goodbye!");
    }


    private void loginAccount() {
        while (true) {
            String username = Inputer.getString("Enter username (or type 'exit' to quit): ");
            if (username.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                return;
            }
            String password = Inputer.getString("Enter password: ");

            if (authService.login(username, password)) {
                System.out.println("\nLogin successful! Welcome, " + authService.getCurrentUser().getUsername() + ".");
                break; // Exit login loop on success
            } else {
                System.out.println("\n--- Invalid credentials. Please try again. ---\n");
            }
        }
    }

    private void registerAccount() {
        view.registerRequired();
        String username;
        while (true) {
            username = Inputer.getInputWithLoop("Creat your ID (or type 'exit' to quit): ", Acceptable.STUDENT_ID, "Invalid input with wrong format!", false);
            if (username.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                return;
            }
            if (!studentService.isIdDuplicate(username) && !authService.isRegisterIdDuplicate(username)) {
                break;
            }
            System.out.println("User with this ID already exists! please try again.");
        }
        String password = Inputer.getString("Enter password: ");
        authService.register(username, password);
        System.out.println("\nRegister successful! Welcome, ");
    }


    /**
     * Displays and handles the menu for Admin users.
     */
    private void runAdminMenu() {
        while (true) {
            view.displayAdminMenu();
            int choice = Inputer.getInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    studentController.addStudent();
                    break;
                case 2:
                    studentController.listStudents();
                    break;
                case 3:
                    mountainController.displayMountains();
                    break;
                case 4:
                    studentController.findStudentById();
                    break;
                case 5:
                    studentController.updateStudent();
                    break;
                case 6:
                    studentController.deleteStudent();
                    break;
                case 7:
                    studentController.findStudentByName();
                    break;
                case 8:
                    studentController.filterStudentByCampus();
                    break;
                case 9:
                    statisticController.showStatistics();
                    break;
                case 10:
                    authService.logout();
                    return; // Logout and exit the menu loop
                default:
                    System.out.println("This function is not available.");
            }
        }
    }

    /**
     * Displays and handles the menu for regular Users (Students).
     */
    private void runUserMenu() {
        if (authService.getCurrentUser() == null) return;
        String studentId = authService.getCurrentUser().getStudentId().toLowerCase();
        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("Error: This user account is not linked to a student profile.");
            return;
        }

        while (true) {
            studentService.LoadFromFile();
            view.displayUserMenu(authService.getCurrentUser().getUsername().toUpperCase());
            int choice = Inputer.getInt("Enter your choice: ");
            Student me = studentService.findStudentById(studentId);
            switch (choice) {
                case 1: // View My Information
                    if (me != null) {
                        view.viewStudent(me);
                    } else {
                        System.out.println("Could not find your student profile.");
                    }
                    break;
                case 2:
                    if (me == null) {
                        authController.addStudentWithRoleUser();
                    } else System.out.println("You have already registered Hiking!!\n");
                    break;
                case 3: // Update My Information
                    studentController.updateOwnProfile(studentId);
                    break;
                case 4: // View Mountain List
                    mountainController.displayMountains();
                    break;
                case 5: // Logout
                    authService.logout();
                    return; // Exit the menu loop
                default:
                    System.out.println("This function is not available.");
            }
        }
    }
}
