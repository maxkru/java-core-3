package Race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Race {

    private CountDownLatch finishLatch = new CountDownLatch(MainClass.CARS_COUNT);
    private CountDownLatch startLatch = new CountDownLatch(MainClass.CARS_COUNT);
    private Lock firstFinishLock = new ReentrantLock();

    public CountDownLatch getFinishLatch() {
        return finishLatch;
    }

    public CountDownLatch getStartLatch() {
        return startLatch;
    }

    public Lock getFirstFinishLock() {
        return firstFinishLock;
    }

    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
