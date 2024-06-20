import java.util.Scanner;

public class TakeOutSimulator {
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    TakeOutSimulator(Customer customer, FoodMenu menu, Scanner input) {
        this.customer = customer;
        this.menu = menu;
        this.input = input;
    }

    private <T> T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever) {
        while (true) {
            System.out.println(userInputPrompt);
            try {
                int userInput = input.nextInt();
                T result = intUserInputRetriever.produceOutputOnIntUserInput(userInput);
                return result;
            } catch (IllegalArgumentException e) {
                System.out.println("Please provide an integer as a valid choice!!");
            }
        }
    }

    public boolean shouldSimulate() {
        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program:";
        IntUserInputRetriever<Boolean> inputRetriever = new IntUserInputRetriever<>() {
            @Override
            public Boolean produceOutputOnIntUserInput(int selection) {
                int lowestPrice = menu.getLowestCostFood().getPrice();
                int currentMoney = customer.getMoney();
                if (selection == 1 && (currentMoney >= lowestPrice)) {
                    return true;
                }
                if (selection == 0 || (currentMoney < lowestPrice)) {
                    System.out.println("You don't have enough money to continue shopping:(");
                    return false;
                } else {
                    throw new IllegalArgumentException("Inside shouldSimulate...");
                }

            }
        };
        return getOutputOnIntInput(userPrompt, inputRetriever);
    }

    public Food getMenuSelection() {
        String userPrompt = String.format("Today's Menu Options!\n %s \n Choose a menu item!:", this.menu.toString());
        IntUserInputRetriever<Food> inputMenuSelection = new IntUserInputRetriever<>() {
            @Override
            public Food produceOutputOnIntUserInput(int selection) {
                Food food = menu.getFood(selection);
                if (food == null) {
                    throw new IllegalArgumentException("Inside getMenuSelection ...");
                } else {
                    return food;
                }
            }
        };
        return getOutputOnIntInput(userPrompt, inputMenuSelection);
    }

    public boolean isStillOrderingFood() {
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT:";
        IntUserInputRetriever<Boolean> inputShoppinOption = new IntUserInputRetriever<>() {
            @Override
            public Boolean produceOutputOnIntUserInput(int selection) {
                if (selection == 1) {
                    return true;
                }
                if (selection == 0) {
                    return false;
                } else {
                    throw new IllegalArgumentException("INside isStillOrderingFood...");
                }
            }
        };
        return getOutputOnIntInput(userPrompt, inputShoppinOption);
    }

    public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Processing the payment... \n");
        int totalItemsPrice = shoppingBag.getTotalPrice();
        int afterPaymentBalance = this.customer.getMoney() - totalItemsPrice;
        this.customer.setMoney(afterPaymentBalance);
        System.out.println("You have have successfully paid " + totalItemsPrice + " Rwf");
        System.out.println("Your Balance is: " + this.customer.getMoney() + " Rwf");
        System.out.println("Thank you and enjoy your food!");

    }

    public void takeOutPrompt() {
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = this.customer.getMoney();
        while (true) {
            System.out.println("Your current Balance is: " + customerMoneyLeft + " Rwf");
            Food food = getMenuSelection();
            if (customerMoneyLeft >= food.getPrice()) {
                customerMoneyLeft -= food.getPrice();
                shoppingBag.addItem(food);
            } else {
                System.out.println("Seems like your wallet is not that big enough for " + food.getName() + " Cost: "
                        + food.getPrice() + " Rwf");
                System.out.println("Please choose another bite!");
                boolean stillOrdering = isStillOrderingFood();
                if (stillOrdering == true) {
                    continue;
                }
                if (stillOrdering == false) {
                    checkoutCustomer(shoppingBag);
                }
            }
        }
    }

    public void startTakeOutSimulator() {
        System.out.println("Hello, Welcome to this Restaurant!");
        System.out.println("Welcome, " + this.customer.getName());
        boolean shouldContinue = shouldSimulate();
        if (shouldContinue == true) {
            takeOutPrompt();
        } else {
            System.out.println("Thank you for visiting us! Have a good day!");
        }
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public FoodMenu getMenu() {
        return this.menu;
    }

    public Scanner getInput() {
        return this.input;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setMenu(FoodMenu menu) {
        this.menu = menu;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
