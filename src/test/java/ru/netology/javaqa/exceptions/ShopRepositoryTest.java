package ru.netology.javaqa.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void shouldRemoveById() {

        Product product1 = new Product(11, "Cheese", 1000);
        Product product2 = new Product(12, "Bread", 100);
        Product product3 = new Product(15, "Butter", 600);

        ShopRepository products = new ShopRepository();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        products.removeById(12);

        Product[] expected = {product1, product3};
        Product[] actual = products.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGenerateNotFoundException() {

        Product product1 = new Product(11, "Cheese", 1000);
        Product product2 = new Product(12, "Bread", 100);
        Product product3 = new Product(15, "Butter", 600);

        ShopRepository products = new ShopRepository();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            products.removeById(14);
        });
    }
}