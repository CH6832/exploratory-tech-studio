public class Main {
    public static void main(String[] args) {
        // Get the singleton instance
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        // Verify that both instances are the same
        System.out.println("Singleton instance addresses: " + singleton1 + " and " + singleton2);

        // Perform some action using the singleton instance
        singleton1.performAction();
    }
}
