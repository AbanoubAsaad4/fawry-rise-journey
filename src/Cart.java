import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> products;
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


    public void add(Product product, int quantity) {
        String name = product.getName();
        if(!Market.isInMarket(name))
            System.out.printf("Can't Add %s, Invalid product name\n\n", name);
        else if(!Market.isInStock(name,quantity))
            System.out.printf("Can't Add %s, There's no enough stock of this product\n\n", name);
        else if(Market.getProduct(name) instanceof Expirable && ((Expirable) Market.getProduct(name)).isExpired())
            System.out.printf("Can't Add %s, Expired Product\n\n", name);
        else
        {
            if(products.containsKey(product)) {
                products.put(product,products.get(product) + quantity);
            }
            else {
                Market.reduceProductQuantity(name,quantity);
                products.put(product,quantity);
            }
        }
    }

    public double getProductsCost() {
        productsCost = 0;
        for (Map.Entry<Product,Integer> product : products.entrySet()) {
            double productCost = product.getKey().price;
            productsCost = productsCost + (productCost * product.getValue());
        }
        return productsCost;
    }

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
}
