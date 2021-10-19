package ThirdTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException
    {
        GroupJournal journal = new GroupJournal();
        int nWeeks = 3;

        var threads = new ArrayList<Thread>();

        threads.add(new Thread(new Teacher("Lecturer", nWeeks, journal)));
        threads.add(new Thread(new Teacher("Assistant 1", nWeeks, journal)));
        threads.add(new Thread(new Teacher("Assistant 2", nWeeks, journal)));
        threads.add(new Thread(new Teacher("Assistant 3", nWeeks, journal)));

        for (var thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        journal.print();
    }
}
