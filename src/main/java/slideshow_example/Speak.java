package slideshow_example;

/**
 * Define Speak interface in which the concrete classes choose
 * to instantiate a particular object. This creates a "polymorphism"
 * in which subclasses are tasked with creating the instance of a class.
 */

public interface Speak {
    // declare abstract method, which will be implemented in the concrete classes
    void makeSound();
}
