package slideshow_example;

/**
 * Implement the Speak interface
 */

public class Hermione implements Speak {
    @Override
    public void makeSound() {
        {
            System.out.println("It's Leviosa, not Leviosar");
        }
    }
}
