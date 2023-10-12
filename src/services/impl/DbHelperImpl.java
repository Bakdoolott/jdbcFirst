package services.impl;

import services.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHelperImpl implements DbHelper {
    @Override
    public PreparedStatement getStatement(String sql) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mirla\\Downloads\\storeDb.db");
            PreparedStatement ps = connection.prepareStatement(sql);
            return ps;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Произошла ошибка при подключении к БД");
        }
    }
}
