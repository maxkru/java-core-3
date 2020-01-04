package MultiFunctionalPrinter;

public class MultiFunctionalPrinter {

    private final Object printLock = new Object();
    private final Object copyLock = new Object();

    public void print(int userID) {
        synchronized (printLock) {
            System.out.println("Printing for user " + userID + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Printed for user " + userID + ".");
        }
    }

    public void copy(int userID) {
        synchronized (copyLock) {
            System.out.println("Copying for user " + userID + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Copied for user " + userID + ".");
        }
    }
}

