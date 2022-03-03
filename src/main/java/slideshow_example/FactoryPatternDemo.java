package slideshow_example;

/**
 * The SpeakFactory is used to get Speak object of the concrete classes by
 * passing information (CHER / CAT / ... ) to SpeakFactory to get the required
 * object type.
 */

public class FactoryPatternDemo {
    public static void main(String[] args) {
        SpeakFactory speakFactory = new SpeakFactory();

        Speak sound1 = speakFactory.getSound("CHER"); // gets object of Cher
        sound1.makeSound(); // call method from conrete class

        Speak sound2 = speakFactory.getSound("FREEDOMCONVOY");
        sound2.makeSound();

        Speak sound3 = speakFactory.getSound("CAT");
        sound3.makeSound();

        Speak sound4 = speakFactory.getSound("BEE");
        sound4.makeSound();

        Speak sound5 = speakFactory.getSound("HERMIONE");
        sound5.makeSound();
    }
}
