import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Abanoub", 100000);

        Product Cheese = new ExpirableShippableProduct("Cheese", 100, 10, 15, 200, LocalDate.of(2025, 12, 1));
        Product Biscuits = new ExpirableShippableProduct("Biscuits", 150, 5, 15, 700, LocalDate.of(2025, 11, 1));
        Product TV = new ShippableProduct("TV", 5000, 3, 100, 3000);
        Product ScratchCard = new Product("ScratchCard", 50, 20);

        Market.addProduct(Cheese);
        Market.addProduct(Biscuits);
        Market.addProduct(TV);
        Market.addProduct(ScratchCard);

        Cart cart = customer.getCart();
        cart.add(Cheese, 2);
        cart.add(TV, 2);
        cart.add(ScratchCard, 1);
        Checkout.checkout(customer, cart);
    }
}
