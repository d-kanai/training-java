package features;

public class ProductCreateCommandInput {
    public final String productName;
    public final int price;

    public ProductCreateCommandInput(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }
}
