package abstract_factory;

import slideshow_example.Bee;
import slideshow_example.Cat;
import slideshow_example.FreedomConvoy;

/**
 * The Factory class will generate an object of the concrete classes
 * based on the given string input
 */

public class GrouponFactory {
    public GrouponDeal getGroupon(String stay) {
        if(stay == null) {
            return null;
        }
        if(stay.equalsIgnoreCase("HOTEL")) {
            return new Hotel();
        }
        else if(stay.equalsIgnoreCase("MOTEL")) {
            return new Motel();
        }
        else if(stay.equalsIgnoreCase("HOLIDAYINN")) {
            return new HolidayInn();
        }
        return null;
    }
}
