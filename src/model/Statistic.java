package model;

public class Statistic {
    private String mountainCode;
    private String mountainName;
    private int numberOfParticipants;
    private double totalTuitionFee;

    public Statistic(String mountainCode, String mountainName) {
        this.mountainCode = mountainCode;
        this.mountainName = mountainName;
        this.numberOfParticipants = 0;
        this.totalTuitionFee = 0;
    }

    public void addStudent(double tuitionFee) {
        this.numberOfParticipants++;
        this.totalTuitionFee += tuitionFee;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public String getMountainName() {
        return mountainName;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public double getTotalTuitionFee() {
        return totalTuitionFee;
    }

    @Override
    public String toString() {
        String format = "| %-14s| %-29s| %-19d| %,-18.0f |";
        return String.format(format, mountainCode, mountainName, numberOfParticipants, totalTuitionFee);
    }
}