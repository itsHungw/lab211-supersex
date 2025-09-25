package model;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private double tutionFee;

    public Student() {
    }

    public Student(String id, String name, String phone, String email, String mountainCode, double tutionFee) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tutionFee = tutionFee;
    }

    public double getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(double tutionFee) {
        this.tutionFee = tutionFee;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
//        return "Student{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                ", mountainCode='" + mountainCode + '\'' +
//                ", tutionFee=" + tutionFee +
//                '}';

        String format = "| %-14s| %-24s| %-14s| %-24s| %-19s| %-16s|";
        return String.format(format, id, name, phone, email, mountainCode, tutionFee);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
