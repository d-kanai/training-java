package features.product.domain;

import java.util.UUID;

public class Product {

    private final UUID id;
    private final String name;
    private final int price;
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        DRAFT,
        PUBLISHED;

        public static Status fromString(String status) {
            if (status == null) throw new IllegalArgumentException("Status cannot be null");
            try {
                return Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown status: " + status, e);
            }
        }
    }

    private Product(UUID id, String name, int price, Status status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    //NOTE: 永続データからの再構成用途
    public static Product reconstruct(UUID id, String name, Integer price, Status status) {
        return new Product(id, name, price, status);
    }

    public static Product create(String name, int price) {
        if (price < 0) throw new RuntimeException("マイナスは登録できません");
        return new Product(UUID.randomUUID(), name, price, Status.DRAFT);
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }

    public Status status() {
        return status;
    }
}
