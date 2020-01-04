package MultiFunctionalPrinter;

public class MFPMain {
    public static void main(String[] args) {
        int nOfThreads = 20;
        MultiFunctionalPrinter mfp = new MultiFunctionalPrinter();
        Thread[] threads = new Thread[nOfThreads];
        for (int i = 0; i < nOfThreads; i++) {
            int iFinal = i;
            threads[i] = new Thread(() -> {
                System.out.println("User " + iFinal + " approaches MFP.");
                if(iFinal % 2 == 0)
                    mfp.copy(iFinal);
                else
                    mfp.print(iFinal);
                System.out.println("User " + iFinal + " walks away from MFP.");
            });
            threads[i].start();
        }
    }
}
