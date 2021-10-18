package FirstTask;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicBank implements IBank
{
    public static final int NTEST = 10000;
    private final AtomicIntegerArray accounts;
    private AtomicLong ntransacts = new AtomicLong(0);

    public AtomicBank(int n, int initialBalance)
    {
        accounts = new AtomicIntegerArray(n);
        int i;

        for (i = 0; i < accounts.length(); i++)
            accounts.set(i, initialBalance);
    }

    @Override
    public void transfer(int from, int to, int amount) throws InterruptedException
    {
        accounts.addAndGet(from, -amount);
        accounts.addAndGet(to, amount);

        ntransacts.incrementAndGet();

        if (ntransacts.get() % NTEST == 0)
            test();
    }

    private void test()
    {
        AtomicInteger sum = new AtomicInteger(0);

        for (int i = 0; i < accounts.length(); i++) sum.addAndGet(accounts.get(i));
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }

    @Override
    public int size()
    {
        return accounts.length();
    }
}
