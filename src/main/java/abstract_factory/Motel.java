package abstract_factory;

/**
 * Concrete class extends GrouponDeal abstract class. getRate() is an abstract method
 * in the GrouponDeal and must be overridden in the concrete subclasses. Here we define
 * a rate unique to each concrete class (Hotel, Motel, HolidayInn)
 */

public class Motel extends GrouponDeal {
    public void getRate() { rate = 47.95; }
}
