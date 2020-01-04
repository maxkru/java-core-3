package ABC;

public class ABCPrinter {

    private final Object monitor = new Object();
    private char nextToPrint = 'A';

    void printABC(int times) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for(int i = 0; i < times; i++) {

                        while (nextToPrint != 'A') {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print('A');
                        nextToPrint = 'B';
                        monitor.notifyAll();
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                synchronized (monitor) {
                    for(int i = 0; i < times; i++) {

                        while (nextToPrint != 'B') {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print('B');
                        nextToPrint = 'C';
                        monitor.notifyAll();
                    }
                }
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                synchronized (monitor) {
                    for(int i = 0; i < times; i++) {

                        while (nextToPrint != 'C') {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print('C');
                        nextToPrint = 'A';
                        monitor.notifyAll();
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }

}

class APrinter implements Runnable {

    @Override
    public void run() {

    }

}

class BPrinter implements Runnable {

    @Override
    public void run() {

    }

}

class CPrinter implements Runnable {

    @Override
    public void run() {

    }

}
