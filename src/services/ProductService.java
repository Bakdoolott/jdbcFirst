package services;

import models.Product;

import java.util.List;

public interface ProductService {
    String createProduct (String productName, Double price);

    Product findById (Long id);

    String deleteProduct (Long id);

    List<Product> allProduct ();

}
