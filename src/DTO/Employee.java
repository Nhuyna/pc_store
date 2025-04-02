package DTO;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String position;      // Chức vụ
    private double salary;
    private String phoneNumber;   // Số điện thoại
    private String email;         // Email
    private LocalDate dateOfJoining;  // Ngày vào làm
    private String homeAddress;       // Địa chỉ nhà

    public Employee(int id, String name, String position, double salary,
                    String phoneNumber, String email, LocalDate dateOfJoining) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfJoining = dateOfJoining;

    }

        public Employee( String name, String position, double salary,
        String phoneNumber, String email, LocalDate dateOfJoining) {

    this.name = name;
    this.position = position;
    this.salary = salary;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.dateOfJoining = dateOfJoining;

}
    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }
    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    
    public String getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}
