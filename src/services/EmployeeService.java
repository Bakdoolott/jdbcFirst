package services;

import models.Employee;

import java.util.List;

public interface EmployeeService {
    String addEmployee(String name, long storeId, int age);
    Employee findById(Long id);
    List<Employee> findByStore();
    List<Employee> findAll();
}
