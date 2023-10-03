import models.Store;
import services.StoreService;
import services.impl.StoreServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        StoreService storeService =new StoreServiceImpl();

        System.out.println("enter id");
        Long id=scanner.nextLong();
        System.out.println("enter name");
        String name=scanner.next();



        System.out.println(storeService.update(id,name));
    }
}