package features.user.domain;

public enum UserPlan {
    STANDARD("STANDARD", 1),
    VIP("VIP", 0.9);


    private final String name;
    private final double discountRate;

    private UserPlan(final String name, final double discountRate) {
        this.name = name;
        this.discountRate = discountRate;
    }

    public double discountRate() {
        return discountRate;
    }
}
