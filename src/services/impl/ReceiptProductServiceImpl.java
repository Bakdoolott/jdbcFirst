package services.impl;
import models.Product;
import services.DbHelper;
import services.ProductService;
import services.ReceiptProductService;
import services.StoreService;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReceiptProductServiceImpl implements ReceiptProductService {
    Scanner scanner = new Scanner(System.in);
    ProductService productService = new ProductServiceImpl();
    DbHelper dbHelper = new DbHelperImpl();
    @Override
    public String sellProduct(long emplId) {
        long idOfNewReceipt;
        PreparedStatement preparedStatement = dbHelper.getStatement("SELECT max(id) + 1 FROM tb_receipt");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            idOfNewReceipt = resultSet.getLong(1);
            preparedStatement.close();
            resultSet.close();
            double price = 0;

            String continueCycle = "";
            while (continueCycle.isEmpty()){
                String choice;
                System.out.println("Введите id продукта " + productService.allProduct());
                choice = scanner.nextLine();

                preparedStatement = dbHelper.getStatement("INSERT INTO tb_product_receipt (product_id, receipt_id) VALUES (?, ?)");
                preparedStatement.setString(1, choice);
                preparedStatement.setLong(2, idOfNewReceipt);
                preparedStatement.executeUpdate();
                preparedStatement.close();

                preparedStatement = dbHelper.getStatement("SELECT price FROM tb_product WHERE id = ?");
                preparedStatement.setString(1, choice);
                resultSet = preparedStatement.executeQuery();
                price = price + resultSet.getDouble(1);
                preparedStatement.close();
                resultSet.close();
                System.out.println("Чтобы продолжить нажмите Enter");
                continueCycle = scanner.nextLine();

            }

            preparedStatement = dbHelper.getStatement("INSERT INTO tb_receipt (sum, empl_id, date) VALUES (?, ?, datetime('now'))");
            preparedStatement.setDouble(1, price);
            preparedStatement.setLong(2, emplId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "You sold products successfully";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
