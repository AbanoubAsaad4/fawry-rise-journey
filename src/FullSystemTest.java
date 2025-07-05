import java.time.LocalDate;

public class FullSystemTest {
    public static void main(String[] args) {

        // Create products
        Product cheese = new ExpirableShippableProduct("Cheese", 23, 5, 10, 250, LocalDate.of(2026, 8, 6));
        Product expiredMilk = new ExpirableShippableProduct("Milk", 10, 10, 5, 1000, LocalDate.of(2023, 1, 1)); // expired
        Product laptop = new ShippableProduct("Laptop", 23000, 10, 300, 2350);
        Product gum = new ExpirableProduct("Gum", 1, 0, LocalDate.of(2026, 12, 15)); // out of stock
        Product scratchCard = new Product("ScratchCard", 50, 100); // non-expirable, non-shippable
        Product tv = new ShippableProduct("TV", 5000, 2, 1500, 7000);

        // Add products to Market
        Market.addProduct(cheese);
        Market.addProduct(expiredMilk);
        Market.addProduct(laptop);
        Market.addProduct(gum);
        Market.addProduct(scratchCard);
        Market.addProduct(tv);

        // Create a customer
        Customer alice = new Customer("Alice", 100000);
        Cart cart = alice.getCart();

        // Add valid product
        cart.add(cheese, 2); // Valid

        // Add same product again
        cart.add(cheese, 1); // Valid

        // Add expired product
        cart.add(expiredMilk, 2); // Prints expired warning

        // Add out-of-stock product
        cart.add(gum, 1); // Prints out-of-stock

        // Add more than available stock
        cart.add(tv, 5); // Print not enough stock

        // Add with quantity 0
        cart.add(laptop, 0); // Print Invalid

        // Add with quantity 1 (only 1 in stock)
        cart.add(laptop, 1); // Valid

        // Add non-expirable, non-shippable
        cart.add(scratchCard, 2); // Valid

        // Final receipt
        Checkout.receipt(alice, cart);


    /*
        // Another edge case (Won't Print the receipt) Not enough Balance
        cart.add(laptop, 9); // Inefficient balance!

        // Final receipt
        Checkout.receipt(alice, cart);
    */
    }
}
