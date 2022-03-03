package slideshow_example;

/**
 * Implement the Speak interface
 */

public class Bee implements Speak {
    @Override
    public void makeSound() {
        System.out.println("Buzz Buzz, i'm a bee");
    }
}
