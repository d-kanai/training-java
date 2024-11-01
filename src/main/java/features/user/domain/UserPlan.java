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

    // 文字列からUserPlanに変換するメソッド
    public static UserPlan fromString(String planString) {
        for (UserPlan plan : UserPlan.values()) {
            if (plan.name.equalsIgnoreCase(planString)) {
                return plan;
            }
        }
        throw new IllegalArgumentException("No enum constant for name: " + planString);
    }
}
