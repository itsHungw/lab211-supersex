package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String username;
    private String password;
    private Role role;
    private String studentId; // Link to the Student object, can be null for ADMIN

    public User(String username, String password, Role role, String studentId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", studentId='" + studentId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(studentId, user.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentId);
    }
}