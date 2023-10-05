package services;

import models.Employee;

public interface EmployeeService {


    String addEmployee(String name, int storeId, int age);
    Employee findById(Long id);
}
