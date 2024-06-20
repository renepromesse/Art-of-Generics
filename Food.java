
public class Food implements PricedItem<Integer> {
    private String name;
    private String description;
    private int price;

    Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void toString() {
        System.out.println("Enjoy Tacos: " + this.name + "    Cost: $" + this.price);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
