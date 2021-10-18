package FirstTask;

public class AsynchBankTest
{
    public static final int accountsAmount = 10;
    public static final int balance = 10000;

    public static void main(String[] args)
    {
        IBank bank = new Bank(accountsAmount, balance);
        IBank lockBank = new LockBank(accountsAmount, balance);
        IBank atomicBank = new AtomicBank(accountsAmount, balance);
        IBank synchronizedBank = new SynchronizedBank(accountsAmount, balance);

        test(bank);
        //test(lockBank);
        //test(atomicBank);
        //test(synchronizedBank);
    }

    private static void test(IBank bank)
    {
        for (int i = 0; i < accountsAmount; i++)
        {
            TransferThread t = new TransferThread(bank, i, balance);

            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start();
        }
    }
}
