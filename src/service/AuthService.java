package service;

import model.Role;
import model.Student;
import model.User;
import repository.StudentRepository;
import repository.UserRepository;
import utils.Acceptable;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private User currentUser;
    private List<User> users;

    public AuthService() {
        this.userRepository = new UserRepository();
        this.currentUser = null;
        this.studentRepository = new StudentRepository();
        this.users = userRepository.loadFromFile(); // Load users in constructor

        // If no users are loaded, create a default admin
        if (this.users.isEmpty()) {
            User admin = new User("admin", "admin", Role.ADMIN, null);
            this.users.add(admin);
            saveUsers();
            System.out.println("Default admin account created (admin/admin).");
        }
    }

    public void saveUsers() {
        userRepository.save(users);
    }

    public List<User> getUsers() {
        return users;
    }

    public void removeUser(User user) {
        users.remove(user);
    }


    public void loadUser() {
        List<User> loadedUsers = userRepository.loadFromFile();
        if (loadedUsers != null && !loadedUsers.isEmpty()) {
            this.users.clear();
            this.users.addAll(loadedUsers);
            System.out.println("Loaded " + this.users.size() + " users successfully.");
        } else {
            System.out.println("No User loaded from file or file is empty.");
        }
    }


    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public void register(String username, String password) {
        User newUser = new User(username, password, Role.USER, username);
        users.add(newUser);
        saveUsers();
    }

    public boolean login(String username, String password) {
        User user = findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
        System.out.println("You have been logged out.");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAdmin() {
        return this.currentUser != null && this.currentUser.getRole() == Role.ADMIN;
    }

    //user register to hiking (add student with owner ID)
    public void addStudentRoleUser(StudentService studentService, String studentID, String studentName, String studentPhone, String studentEmail, String mountainCode) {
        studentService.addStudent(studentID, studentName, studentPhone, studentEmail, mountainCode);
    }

    public boolean isRegisterIdDuplicate(String id) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public User findUserByID(String id) {
        for (User user : users) {
            if (user.getStudentId() != null && user.getStudentId().equalsIgnoreCase(id)) {
                return user;
            }
        }
        return null;
    }
}
