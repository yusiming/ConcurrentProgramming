/**
 * 在业务中，如果仅仅对写方法加锁，而不对读方法加锁，
 * 有可能会造成脏读问题，因为对写方法加锁，仅仅能保证在任何一个时刻只有一个线程在执行该方法，
 * 但是不保证在方法执行不被剥夺cpu的使用，如果在写方法中，对某个数据赋值之前，被剥夺了使用权，
 * 此时如果去调用读方法，值还未改变，着就会造成两次读取的值不一致
 *
 * @Auther yusiming
 * @Date 2019/2/28 20:30
 */
public class Account {
    private String name;
    private int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public synchronized void setBalance(int balance) {
        try {
            // 这里是为了放大问题
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Account a = new Account("张三", 200);
        new Thread(() -> a.setBalance(300)).start();
        System.out.println(a.getBalance());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance());
    }
}
