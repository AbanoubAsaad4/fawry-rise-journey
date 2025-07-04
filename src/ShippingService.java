import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public static void ship(List<ShippingProduct> products) {
        System.out.println("\n\n** Shipment notice **");

        Map<String,Integer> productCount = new HashMap<>();
        Map<String,Integer> productWeight = new HashMap<>();
        double totalWeight = 0;

        for(ShippingProduct product : products) {
            String name = product.getName();
            int weight = product.getWeight();

            if (productCount.containsKey(name))
                productCount.put(name, productCount.get(name) + 1);
            else
                productCount.put(name,1);
            productWeight.put(name,weight);
            totalWeight += weight;
        }
        for(String product : productCount.keySet()) {
            int quantity = productCount.get(product);
            int weight = productWeight.get(product);

            System.out.printf("%dx  %-15s      %dg\n",quantity,product,weight);
        }

        totalWeight /= 1000.0;
        System.out.printf("Total package weight %.2fkg\n\n",totalWeight);
    }
}