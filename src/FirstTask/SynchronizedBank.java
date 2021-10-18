package FirstTask;

public class SynchronizedBank implements IBank
{
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts;

    public SynchronizedBank(int n, int initialBalance)
    {
        accounts = new int[n];
        int i;

        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;

        ntransacts = 0;
    }

    @Override
    public synchronized void transfer(int from, int to, int amount) throws InterruptedException
    {
        accounts[from] -= amount;
        accounts[to] += amount;

        ntransacts++;

        if (ntransacts % NTEST == 0)
            test();
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
