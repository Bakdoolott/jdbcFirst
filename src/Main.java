import services.ProductService;
import services.impl.ProductServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductServiceImpl();

//        System.out.println("Enter the product");
//        String productName = scanner.next();
//        System.out.println("Enter the price");
//        Long price = scanner.nextLong();
//        System.out.println(productService.CreateProduct(productName,price));

//        System.out.println("Enter the product id");
//        Long id = scanner.nextLong();
//        System.out.println(productService.findById(id));

//        System.out.println("Enter the name of product");
//        String name = scanner.next();
//        System.out.println("Enter the price");
//        Long price = scanner.nextLong();
//        System.out.println("Enter the id");
//        Long id = scanner.nextLong();
//        System.out.println(productService.updateProduct(id,name,price));

        System.out.println("Enter the id");
        Long id = scanner.nextLong();
        System.out.println(productService.deleteProduct(id));
        System.out.println(productService.allProduct());

    }
}