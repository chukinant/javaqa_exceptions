package ru.netology.javaqa.exceptions;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    protected Product[] addToArray(Product[] current, Product product) {
        Product productToAdd = findById(product.id);
        if (productToAdd == null) {
            Product[] tmp = new Product[current.length + 1];
            for (int i = 0; i < current.length; i++) {
                tmp[i] = current[i];
            }
            tmp[tmp.length - 1] = product;
            return tmp;
        } else {
            throw new AlreadyExistsException("Item with ID" + product.id + " already exists");
        }
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void removeById(int id) {
        Product productToRemove = findById(id);
        if (productToRemove != null) {
            Product[] tmp = new Product[products.length - 1];
            int copyToIndex = 0;
            for (Product product : products) {
                if (product.getId() != id) {
                    tmp[copyToIndex] = product;
                    copyToIndex++;
                }
            }
            products = tmp;
        } else {
            throw new NotFoundException("ID " + id + " не найден");
        }
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
