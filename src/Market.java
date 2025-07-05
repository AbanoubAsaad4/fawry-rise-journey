import java.util.HashMap;
import java.util.Map;

public class Market {
    private static final Map<String, Product> availableProducts = new HashMap<>(); // Holds all products of the Market


    public static Map<String, Product> getAvailableProducts() {
        return availableProducts;
    }

    public static Product getProduct(String name) {
        return availableProducts.get(name);
    }

    public static void addProduct(Product product) {
            String productName =  product.getName();
            availableProducts.put(productName, product);
    }

    // Updates the available quantity of the specified product after a purchase
    public static void reduceProductQuantity(String name, int quantity) {
        Product product = availableProducts.get(name);
        int availableQuantity = getAvailableProducts().get(name).getQuantity();
        int newQuantity = availableQuantity - quantity;
        product.setQuantity(newQuantity);
        getAvailableProducts().put(name, product);
    }

    public static boolean isInStock(String name, int quantity) {
         return availableProducts.get(name).getQuantity() >= quantity;
    }

    public static boolean isInMarket(String name) {
        return Market.getAvailableProducts().containsKey(name);
    }
}
