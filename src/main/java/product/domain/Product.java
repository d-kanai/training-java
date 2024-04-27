package product.domain;

import product.presentation.ProductCreateInput;

public class Product {
    private final int userId;
    private final String name;
    private final int price;

    public Product(int userId, String name, int price) {
        this.userId = userId;
        this.name = name;
        this.price = price;
    }

    public static Product draft(ProductCreateInput input) {
        return new Product(
                0,
                input.name,
                input.price
        );
    }
}
