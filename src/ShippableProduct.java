public class ShippableProduct extends Product implements Shippable, ShippingProduct {
    private double shippingCost;
    private int weight; //in Grams

    ShippableProduct(String name, double price, int quantity, double shippingCost, int weight) {
        super(name,price,quantity);
        this.shippingCost = shippingCost;
        this.weight = weight;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
