package slideshow_example;

/**
 * Implement the Speak interface.
 * makeSound() method is inherited and given a method body and definition
 */

public class Cat implements Speak {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}
