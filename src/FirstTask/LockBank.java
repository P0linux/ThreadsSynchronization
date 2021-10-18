package FirstTask;

import java.util.concurrent.locks.ReentrantLock;

public class LockBank implements IBank
{
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    private final ReentrantLock lock;

    public LockBank(int n, int initialBalance)
    {
        accounts = new int[n];
        int i;

        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;

        ntransacts = 0;
        lock = new ReentrantLock();
    }

    @Override
    public void transfer (int from, int to, int amount) throws InterruptedException
    {
        lock.lock();

        accounts[from] -= amount;
        accounts[to] += amount;

        ntransacts++;

        if (ntransacts % NTEST == 0) test();

        lock.unlock();
    }

    private void test()
    {
        int sum = 0;
        for (int account : accounts) sum += account;
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }

    @Override
    public int size()
    {
        return accounts.length;
    }
}
