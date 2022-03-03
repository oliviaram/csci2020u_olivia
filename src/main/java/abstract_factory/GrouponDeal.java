package abstract_factory;

/**
 * Define abstract class for creating an object that the
 * subclasses choose which object to create
 */

abstract class GrouponDeal {
    protected double rate; // variable accessed only by subclasses
    abstract void getRate(); // body-less method
    public void receipt(int nights) {
        System.out.print("Total: $" + (nights*rate));
    }
}
