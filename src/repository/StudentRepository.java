package repository;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final String pathFile = "students.dat";

    public void save(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathFile))) {
            oos.writeObject(students);
            System.out.println("Student data saved to " + pathFile);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }


    public List<Student> load() {
        List<Student> students = new ArrayList<>();
        File file = new File(pathFile);
        if (!file.exists()) {
            System.out.println("Student data not found");
            return students;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathFile))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return students;
    }
}
