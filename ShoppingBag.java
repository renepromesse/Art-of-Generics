import java.util.HashMap;
import java.util.Map;

public class ShoppingBag<T extends PricedItem<Integer>> {
    private Map<T, Integer> shoppingBag;

    ShoppingBag() {
        this.shoppingBag = new HashMap<T, Integer>();
    }

    public void addItem(T item) {
        if (this.shoppingBag.get(item) == null) {
            this.shoppingBag.put(item, 1);
        } else {
            int countItem = this.shoppingBag.get(item) + 1;
            this.shoppingBag.put(item, countItem);
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Integer item : this.shoppingBag.keySet()) {
            totalPrice += item * this.shoppingBag(item);
        }

        return totalPrice;
    }
}