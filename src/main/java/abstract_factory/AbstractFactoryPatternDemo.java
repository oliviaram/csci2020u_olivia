package abstract_factory;

import java.util.Scanner;

/**
 * The abstract class here uses the Factory class to obtain the object of the
 * concrete classes by passing information - hotel, motel, holidayinn
 */

public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        GrouponFactory grouponFactory = new GrouponFactory();

        // user input
        System.out.println("Choose your stay: ");
        String stayType = scan.nextLine();

        System.out.println("Duration (nights) of stay: ");
        int numNights = scan.nextInt(); // scan next integer


        GrouponDeal deal = grouponFactory.getGroupon(stayType); // gets object of specified stay

        System.out.println("[Voucher]");
        System.out.println("Enjoy your " + numNights + " night stay at the " + stayType + ".");
        deal.getRate(); // call method from concrete class
        deal.receipt(numNights); // call method from concrete class
    }
}
