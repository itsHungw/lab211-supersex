package repository;

import model.Role;
import model.Student;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final String pathFile = "users.dat";

    public UserRepository() {
        loadFromFile();
    }

    public void save(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathFile))) {
            oos.writeObject(users);
            System.out.println("User data saved to " + pathFile);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }



    public List<User> loadFromFile() {
        List<User> users = new ArrayList<>();
        File file = new File(pathFile);
        if (!file.exists()) {
            return users;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathFile))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading user file: " + e.getMessage());
        }
        return users;
    }


}