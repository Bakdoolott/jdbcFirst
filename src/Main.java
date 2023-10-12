import models.Employee;
import services.EmployeeService;
import services.ProductService;
import services.ReceiptProductService;
import services.StoreService;
import services.impl.EmployeeServiceImpl;
import services.impl.ProductServiceImpl;
import services.impl.ReceiptProductServiceImpl;
import services.impl.StoreServiceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReceiptProductService receiptProductService = new ReceiptProductServiceImpl();
        StoreService storeService = new StoreServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        ProductService productService = new ProductServiceImpl();
        System.out.println("Добро пожаловать! 🙋‍♂️");
        System.out.println(employeeService.findAll() + "\nКто использует компьютер? \nВведите id");
        long idOfUser = scanner.nextLong();

        while (true) {
            System.out.println("Выберите категорию\n" +
                    "1. Работа с сотрудниками\n" +
                    "2. Работа с продуктами\n" +
                    "3. Работа с магазинами\n" +
                    "4. Продолжить кассовую работу");

            int category = scanner.nextInt();

            switch (category){
                case 1:
                    String continueCycle1 = "";
                    while (continueCycle1.isEmpty()) {
                        System.out.println("1. Добавить нового сотрудника\n" +
                                "2. Найти сотрудника по id\n" +
                                "3. Найти всех сотудников одного магазина");
                        int choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("Введите \n'имя сотрудника', \n'id магазина', \n'возраст сотрудника'");
                                String name = scanner.nextLine();
                                storeService.findAll();
                                Long shopId = scanner.nextLong();
                                int age = scanner.nextInt();
                                System.out.println(employeeService.addEmployee(name, shopId, age));
                                break;
                            case 2:
                                System.out.println(employeeService.findAll() + "\nВыберите id сотрудника");
                                long idOfEmployee = scanner.nextLong();
                                System.out.println(employeeService.findById(idOfEmployee));
                                break;
                            case 3:
                                System.out.println(employeeService.findByStore());
                                break;
                        }
                        System.out.println("Чтобы продолжить нажмите Enter");
                        scanner.nextLine();
                        continueCycle1 = scanner.nextLine();
                    }
                    break;
                case 2:
                    String continueCycle2 = "";
                    while (continueCycle2.isEmpty()) {
                        System.out.println("1. Добавить продукт\n" +
                                "2. Найти продукт\n" +
                                "3. Удалить продукт\n" +
                                "4. Вывести всех продуктов");
                        int choice1 = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice1) {
                            case 1:
                                System.out.println("Введите имя продукта и цену");
                                String productName = scanner.nextLine();
                                double price = scanner.nextDouble();
                                System.out.println(productService.createProduct(productName, price));
                                break;
                            case 2:
                                System.out.println(productService.allProduct() + "\nВыберите id продукта");
                                long productId = scanner.nextLong();
                                System.out.println(productService.findById(productId));
                                break;
                            case 3:
                                System.out.println(productService.allProduct() + "Выберите id продукта");
                                long productId1 = scanner.nextLong();
                                System.out.println(productService.deleteProduct(productId1));
                                break;
                            case 4:
                                System.out.println(productService.allProduct());
                                break;
                        }
                        System.out.println("Чтобы продолжить нажмите Enter");
                        continueCycle2 = scanner.nextLine();
                    }
                    break;
                case 3:
                    String continueCycle3 = "";
                    while (continueCycle3.isEmpty()) {
                        System.out.println("1. Добавить новый магазин\n" +
                                "2. Найти магазин по id\n" +
                                "3. Вывести всех магазинов\n" +
                                "4. Сменить имя магазина");
                        int choice2 = scanner.nextInt();
                        switch (choice2) {
                            case 1:
                                System.out.println("Придумайте имя магазину");
                                String newStoreName = scanner.nextLine();
                                System.out.println(storeService.saveStore(newStoreName));
                                break;
                            case 2:
                                System.out.println(storeService.findAll() + "\nВыберите id магазина");
                                long storeId = scanner.nextLong();
                                System.out.println(storeService.findById(storeId));
                                break;
                            case 3:
                                System.out.println(storeService.findAll());
                                break;
                            case 4:
                                System.out.println(storeService.findAll() + "\nВыберите id магазина и придумайте название");
                                long updateStoreId = scanner.nextLong();
                                String storeName = scanner.nextLine();
                                System.out.println(storeService.update(updateStoreId, storeName));
                                break;
                        }
                        System.out.println("Чтобы продолжить нажмите Enter");
                        scanner.nextLine();
                        continueCycle3 = scanner.nextLine();
                    }
                    break;
                case 4:
                        System.out.println(receiptProductService.sellProduct(idOfUser));
                    break;

                default:
                    System.out.println("Ввели неправильное число");
                    break;
            }
        }
    }
}