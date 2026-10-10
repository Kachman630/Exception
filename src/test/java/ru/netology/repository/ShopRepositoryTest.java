package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Молоко", 100);
        Product product2 = new Product(2, "Хлеб", 50);

        repo.add(product1);
        repo.add(product2);

        // Удаляем существующий товар
        repo.removeById(1);

        // Проверяем, что остался только второй товар
        Product[] expected = {product2};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Молоко", 100);
        repo.add(product);

        // Проверяем, что при удалении несуществующего ID (99) выбрасывается нужное исключение
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            repo.removeById(99);
        });

        // Проверяем текст сообщения исключения
        assertEquals("Element with id: 99 not found", exception.getMessage());
    }
}