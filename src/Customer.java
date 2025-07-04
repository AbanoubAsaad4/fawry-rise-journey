public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer() {

    }

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        cart = new Cart();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean pay(double amount) {
        if (amount > balance)
            return false;
        balance -= amount;
        return true;
    }
}
