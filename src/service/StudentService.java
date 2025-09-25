package service;

import model.Role;
import model.Student;
import model.User;
import repository.StudentRepository;
import utils.Acceptable;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final String PASSWORD = "123456";
    private final StudentRepository studentRepository;
    private final List<Student> students;
    private final AuthService authService;

    public StudentService(AuthService authService) {
        this.studentRepository = new StudentRepository();
        this.students = studentRepository.load();
        this.authService = authService;
    }

    public void addStudent(String studentID, String studentName, String studentPhone, String studentEmail, String mountainCode) {
        // Calculate tuition fee with discount for Viettel or VNPT phone numbers
        double tutionFee = 6000000;
        if (studentPhone.matches(Acceptable.VIETTEL_VALID) || studentPhone.matches(Acceptable.VNPT_VALID)) {
            tutionFee = 6000000 * 0.65;
        }
        Student student = new Student(studentID, studentName, studentPhone, studentEmail, mountainCode, tutionFee);
        students.add(student);
        studentRepository.save(students);

        // Create user for the new student
        User user = new User(student.getId(), PASSWORD, Role.USER, student.getId());
        authService.getUsers().add(user);
        authService.saveUsers();
    }

    public void updateStudent(Student student, String name, String phone, String email, String codeMountain) {
        // Update fields only if new data is provided
        if (name != null && !name.isEmpty()) {
            student.setName(name);
        }
        if (phone != null && !phone.isEmpty()) {
            student.setPhone(phone);
        }
        if (email != null && !email.isEmpty()) {
            student.setEmail(email);
        }
        if (codeMountain != null && !codeMountain.isEmpty()) {
            student.setMountainCode(codeMountain);
        }
        if(Acceptable.isValid(phone, Acceptable.VIETTEL_VALID) || Acceptable.isValid(phone, Acceptable.VNPT_VALID)) {
            student.setTutionFee(6000000 * 0.65);
        }else student.setTutionFee(6000000);

        studentRepository.save(students);
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void deleteStudent(Student student) {
        students.remove(student);
        studentRepository.save(students);

        User user = authService.findUserByID(student.getId());
        if (user != null) {
            authService.removeUser(user);
            authService.saveUsers();
        }
    }

    public List<Student> findStudentByName(String name) {
        List<Student> studentsListContainName = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                studentsListContainName.add(student);
            }
        }
        return studentsListContainName;
    }

    public List<Student> filterByCampusCode(String campusCode) {
        List<Student> studentsListContainCampusCode = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().toLowerCase().contains(campusCode)) {
                studentsListContainCampusCode.add(student);
            }
        }
        return studentsListContainCampusCode;
    }

    public boolean isIdDuplicate(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPhoneDuplicate(String phone) {
        for (Student student : students) {
            if (student.getPhone().equalsIgnoreCase(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailDuplicate(String email) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public void saveToFile() {
        studentRepository.save(students);
    }

    public void LoadFromFile() {
        List<Student> loadedStudents = studentRepository.load();
        if (loadedStudents != null && !loadedStudents.isEmpty()) {
            this.students.clear();
            this.students.addAll(loadedStudents);
            System.out.println("Loaded " + this.students.size() + " students successfully.");
        } else {
            System.out.println("No students loaded from file or file is empty.");
        }
    }
}
