
import java.util.*;

class Products {
    double price;
    int quantity;

    public Products(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }
}

public class sales {

    public static Map<String, Integer> countProducts(List<Products> sales) {
        Map<String, Integer> countByPriceRange = new HashMap<>();
        for (Products sale : sales) {
            String priceRange = getPriceRange(sale.price);
            countByPriceRange.put(priceRange, countByPriceRange.getOrDefault(priceRange, 0) + sale.quantity);
        }
        return countByPriceRange;
    }

    public static Map<String, Double> calculateRevenueByPriceRange(List<Products> sales) {
        Map<String, Double> revenueByPriceRange = new HashMap<>();
        for (Products sale : sales) {
            String priceRange = getPriceRange(sale.price);
            revenueByPriceRange.put(priceRange, revenueByPriceRange.getOrDefault(priceRange, 0.0) + sale.getTotal());
        }
        return revenueByPriceRange;
    }

    public static String getPriceRange(double price) {
        if (price < 50) {
            return "$0-50";
        } else if (price < 100) {
            return "$50-100";
        } else if (price < 200) {
            return "$100-200";
        } else {
            return "Above $200";
        }
    }

    public static void main(String[] args) {
        // Sample product sales
        List<Products> sales = new ArrayList<>();
        sales.add(new Products(45, 5));
        sales.add(new Products(95, 9));
        sales.add(new Products(100, 6));
        sales.add(new Products(250, 4));

        // Count products by price range
        Map<String, Integer> productCounts = countProducts(sales);

        // Calculate revenue by price range
        Map<String, Double> revenueByPriceRange = calculateRevenueByPriceRange(sales);

        // Display results
        System.out.println("Products sold by price range:");
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " products");
        }

        System.out.println("\nRevenue generated by price range:");
        for (Map.Entry<String, Double> entry : revenueByPriceRange.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}
