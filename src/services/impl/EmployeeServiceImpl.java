package services.impl;

import models.Employee;
import services.DbHelper;
import services.EmployeeService;
import services.StoreService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImpl implements EmployeeService {
    Scanner scanner = new Scanner(System.in);
    DbHelper dbHelper = new DbHelperImpl();
    StoreService storeService=new StoreServiceImpl();
    @Override
    public String addEmployee(String name, long storeId, int age) {
        PreparedStatement preparedStatement = dbHelper.getStatement("INSERT INTO tb_employee(name, store_id, age) VALUES(?, ?, ?)");
        try {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, storeId);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Employee was added successfully";
    }

    @Override
    public Employee findById(Long id) {
        PreparedStatement preparedStatement = dbHelper.getStatement("SELECT * FROM tb_employee WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            ResultSet resultSet=preparedStatement.executeQuery();
            Employee employee = new Employee();
            while (resultSet.next()){
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setStore(storeService.findById(resultSet.getLong("store_id")));
            }
            preparedStatement.close();
            resultSet.close();
            return employee;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> findByStore() {
        System.out.println("Выберите название магазина");
        System.out.println(storeService.findAll());
        String nameOfStore = scanner.next();
        PreparedStatement preparedStatement = dbHelper.getStatement("SELECT * FROM tb_employee e JOIN tb_store s on e.store_id = s.id WHERE s.name LIKE ?");
        List<Employee> employees = new ArrayList<>();
        try {
            preparedStatement.setString(1, nameOfStore);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setStore(storeService.findById(resultSet.getLong("store_id")));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement preparedStatement = dbHelper.getStatement("SELECT * FROM tb_employee");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setStore(storeService.findById(resultSet.getLong("store_id")));
                employee.setAge(resultSet.getInt("age"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }
}
