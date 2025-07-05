import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private final Map<Product,Integer> products;
    private double productsCost;
    private double shippingCost;

    public Cart() {
        products = new HashMap<>();
        productsCost = 0;
        shippingCost = 0;
    }

    public Map<Product,Integer> getProducts() {
        return products;
    }

    // Returns the price of all products in the cart
    public double getProductsCost() {
        productsCost = 0;
        for (Map.Entry<Product,Integer> product : products.entrySet()) {
            double productCost = product.getKey().price;
            productsCost = productsCost + (productCost * product.getValue());
        }
        return productsCost;
    }

    // Returns the shipping price of all products in the cart
    public double getShippingCost() {
        shippingCost = 0;
        for (Map.Entry<Product,Integer> product : products.entrySet()) {
            if(product.getKey() instanceof Shippable) {
            double productShippingCost = ((Shippable) product.getKey()).getShippingCost();
            shippingCost = shippingCost + (productShippingCost * product.getValue());
            }
        }
        return shippingCost;
    }

    public double getTotalCost() {
        return getProductsCost() + getShippingCost();
    }

    // Returns a list of Shippable Products in the cart
    public List<ShippingProduct> getShippingProducts() {
        List<ShippingProduct> shippingProducts = new ArrayList<>();
        for (Map.Entry<Product,Integer> product : products.entrySet()) {
            if(product.getKey() instanceof ShippingProduct) {
                for(int i = 0; i < product.getValue(); i++)
                {
                    shippingProducts.add((ShippingProduct) product.getKey());
                }
            }
        }
        return shippingProducts;
    }

    // Adding a product into the cart
    public void add(Product product, int quantity) {
        String name = product.getName();
        if(!Market.isInMarket(name))
            System.out.printf("Sorry, %s is not in Market right now!\n\n", name); // Checks if the product is in Market
        else if(!Market.isInStock(name,quantity))
            System.out.printf("Can't Add %s, There's no enough stock of this product\n\n", name);  // Checks if the product is in Stock
        else if(Market.getProduct(name) instanceof Expirable && ((Expirable) Market.getProduct(name)).isExpired())
            System.out.printf("Can't Add %s, Expired Product\n\n", name); // Checks if the product is expired
        else if (quantity < 1)
            System.out.printf("Can't Add %s, Invalid quantity of product\n\n", name); // Checks if the quantity is non-positive
        else
        {

            if(products.containsKey(product))
                products.put(product,products.get(product) + quantity); // Increase quantity if product already exists in the cart
            else
                products.put(product,quantity);
            Market.reduceProductQuantity(name,quantity);
        }
    }

}
