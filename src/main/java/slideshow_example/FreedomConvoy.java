package slideshow_example;

/**
 * Implement the Speak interface
 */
public class FreedomConvoy implements Speak {
    @Override
    public void makeSound() {
        System.out.println("Ew, vaccines!");
    }
}
