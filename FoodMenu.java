import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class FoodMenu {
    private List<Food> menu = new ArrayList<>();

    FoodMenu() {
        Food item1 = new Food("Chicken Chest", "Half of a chicken", 8000);
        Food item2 = new Food("Beef Stagnof", "Very tasty", 7500);
        Food item3 = new Food("Special Omlette", "Rich in proteins", 6000);

        this.menu.add(item1);
        this.menu.add(item2);
        this.menu.add(item3);
    }

    @Override
    public String toString() {
        String menuItems = "";
        int count = 1;
        for (Food food : this.menu) {
            menuItems += count + ". " + food.getName() + " :     " + food.getDescription() + "    Cost:  "
                    + food.getPrice() + " Rwf \n";
            count++;
        }
        return menuItems;
    }

    public Food getFood(int index) {
        try {
            return this.menu.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Food getLowestCostFood() {
        Food lowest = this.menu.stream().min(Comparator.comparingInt(Food::getPrice)).get();
        return lowest;
    }

}