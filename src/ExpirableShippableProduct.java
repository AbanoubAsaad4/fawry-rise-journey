import java.time.LocalDate;

public class ExpirableShippableProduct extends Product implements Expirable, Shippable, ShippingProduct {
    private LocalDate expirationDate;
    private double shippingCost;
    private int weight; //in Grams

    public ExpirableShippableProduct(String name, double price, int quantity, double shippingCost, int weight, LocalDate expirationDate) {
        super(name, price, quantity);
        this.shippingCost = shippingCost;
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public double getShippingCost() {
        return shippingCost;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
