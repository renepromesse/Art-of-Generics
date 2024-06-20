import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please Enter your name: ");
        String customerName = input.next();

        System.out.println("Right, " + customerName + " Enter your balance: ");
        int money = input.nextInt();
        System.out.println("=====================OKAY=================");
        Customer customer = new Customer(customerName, money);

        FoodMenu menu = new FoodMenu();

        TakeOutSimulator tkOutSimulator = new TakeOutSimulator(customer, menu, input);

        tkOutSimulator.startTakeOutSimulator();
    }
}
