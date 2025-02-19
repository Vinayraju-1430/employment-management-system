package com.cjp;
import java.io.*;
import java.util.*;

class EmployeeManager {
    private List<Employee> employees;
    private final String FILE_NAME = "employees.dat";

    public EmployeeManager() {
        employees = new ArrayList<>();
        loadEmployees();
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
        saveEmployees();
        System.out.println("Employee added successfully!");
    }

    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    public Employee searchEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public void updateEmployee(int id, String name, int age, String dept, double salary) {
        Employee emp = searchEmployee(id);
        if (emp != null) {
            emp.setName(name);
            emp.setAge(age);
            emp.setDepartment(dept);
            emp.setSalary(salary);
            saveEmployees();
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void deleteEmployee(int id) {
        Employee emp = searchEmployee(id);
        if (emp != null) {
            employees.remove(emp);
            saveEmployees();
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    private void loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            employees = new ArrayList<>();
        }
    }
}
