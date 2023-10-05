package services.impl;

import models.Employee;
import services.DbHelper;
import services.EmployeeService;
import services.StoreService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    DbHelper dbHelper = new DbHelperImpl();
    StoreService storeService=new StoreServiceImpl();
    @Override
    public String addEmployee(String name, int storeId, int age) {
        PreparedStatement preparedStatement = dbHelper.getStatement("INSERT INTO tb_employee(name, store_id, age) VALUES(?, ?, ?)");
        try {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, storeId);
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
            Employee employee=new Employee();
            while (resultSet.next()){
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setStore(storeService.findById(resultSet.getLong("store_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
