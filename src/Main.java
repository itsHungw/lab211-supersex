import controller.MainController;
import repository.StudentRepository;
import service.AuthService;
import service.MountainService;
import service.StudentService;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        // Initialize all services and the view
        AuthService authService = new AuthService();
        StudentService studentService = new StudentService(authService);
        MountainService mountainService = new MountainService();

        ConsoleView view = new ConsoleView();

        // Create the main controller and run the application
        MainController controller = new MainController(authService, studentService, mountainService, view);
        controller.run();
    }
}