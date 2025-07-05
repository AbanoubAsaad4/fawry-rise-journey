import java.util.List;
import java.util.Map;

public class Checkout {
    public static void receipt(Customer customer, Cart cart) {
        Map<Product,Integer> customerProducts = cart.getProducts();

        if(customerProducts.isEmpty())
            System.out.println("Error! Cart is EMPTY.");  // Checks if the cart is empty
        else if(!customer.pay(cart.getTotalCost()))
            System.out.println("Error! Insufficient balance."); // Checks if the customer's balance is not enough
        else {
            List<ShippingProduct> shippingProducts = cart.getShippingProducts();
            ShippingService.ship(shippingProducts); // Prints Shipment notice
            checkout(customerProducts,cart); // Prints Checkout receipt
        }
    }

    private static void checkout(Map<Product,Integer> customerProducts, Cart cart) {
        System.out.println("** Checkout receipt **");

        for (Map.Entry<Product,Integer> product : customerProducts.entrySet()) {
            String name = product.getKey().getName();
            double pricePerProduct = product.getKey().getPrice();
            int quantity = product.getValue();
            double price = pricePerProduct * quantity;

            System.out.printf("%dx  %-15s      %.2f\n",quantity,name,price);
        }
        System.out.println("----------------------\n");

        double subTotal = cart.getProductsCost();
        double shippingCost = cart.getShippingCost();
        double amount = cart.getTotalCost();

        System.out.printf("%-15s          $%.2f\n","SubTotal", subTotal);
        System.out.printf("%-15s          $%.2f\n","ShippingCost", shippingCost);
        System.out.printf("%-15s          $%-15.2f\n","Amount", amount);
    }
}
