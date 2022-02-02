package csci2020u.lab02;

public class SimpleTest {
    public String getGreeting() {
        return "Hell World!";
    }

    public static void main(String[] args) {
        System.out.println(new SimpleTest().getGreeting());
    }
}