package slideshow_example;

/**
 * The Factory class will generate an object of the concrete classes
 * based on the given string input
 */

public class SpeakFactory {
    public Speak getSound(String message) {
        if(message == null) {
            return null;
        }
        if(message.equalsIgnoreCase("CAT")) {
            return new Cat(); // instantiation case
        }
        else if(message.equalsIgnoreCase("BEE")) {
            return new Bee();
        }
        else if(message.equalsIgnoreCase("FREEDOMCONVOY")) {
            return new FreedomConvoy();
        }
        else if(message.equalsIgnoreCase("CHER")) {
            return new Cher();
        }
        else if(message.equalsIgnoreCase("HERMIONE")) {
            return new Hermione();
        }
        return null;
    }
}
