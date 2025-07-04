import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Checkout {
    public static void checkout(Customer customer, Cart cart) {
        Map<Product,Integer> customerProducts = cart.getProducts();

        if(customerProducts.isEmpty())
            System.out.println("Error! Cart is EMPTY.");
        else if(!customer.pay(cart.getTotalCost())) {
            System.out.println("Error! Insufficient balance.");
        }
        else {
            List<ShippingProduct> shippingProducts = new ArrayList<>();

            for(Map.Entry<Product,Integer> product : customerProducts.entrySet()) {
                if(product.getKey() instanceof ShippingProduct) {
                    int quantity = product.getValue();

                    for(int i = 0; i < quantity; i++)
                        shippingProducts.add((ShippingProduct) product.getKey());
                }
            }

            ShippingService.ship(shippingProducts);
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
}
