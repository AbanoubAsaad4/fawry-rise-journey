import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public static void ship(List<ShippingProduct> products) {

        Map<ShippingProduct,Integer> shippableProducts = new HashMap<>();
        double totalWeight = 0;

        for (ShippingProduct product : products) {
            if (shippableProducts.containsKey(product)) {
                shippableProducts.put(product,shippableProducts.get(product) + 1);
            }
            else {
                shippableProducts.put(product,1);
            }
            totalWeight += product.getWeight();
        }

            System.out.println("\n\n** Shipment notice **");
        if(totalWeight != 0) {
            for(Map.Entry<ShippingProduct,Integer> product : shippableProducts.entrySet()) {
                int quantity = product.getValue();
                int weight = product.getKey().getWeight();
                String name = product.getKey().getName();

                System.out.printf("%dx  %-15s      %dg\n",quantity,name,weight);
            }

            totalWeight /= 1000.0;
            System.out.printf("Total package weight %.2fkg\n\n",totalWeight);
        }
        else
            System.out.println("There's no shippable items!\n");
    }
}