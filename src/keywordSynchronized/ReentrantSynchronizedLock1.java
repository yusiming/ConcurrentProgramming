package keywordSynchronized;

/**
 * 一个同步方法可以调用另一个同步方法，但是注意：
 * 如果两个方法使用的锁是一致的，那么如果一个线程已经拥有某个对象的锁，再次申请另一个方法时，仍然会获得该对象的锁
 * 如果两个方法使用的锁不是一致的，那么，如果一个线程已经拥有某个对象的锁，再次申请另一个方法，
 * 如果方法已被锁定，那么当前线程就会堵塞，这也有可能会造成死锁
 * synchronized获得的锁是可重入的
 *
 * @Auther yusiming
 * @Date 2019/2/28 20:50
 */
public class ReentrantSynchronizedLock1 {
    public synchronized void fun1() {
        System.out.println("fun1 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 由于都是使用的this，所以肯定可以进入fun2()
        fun2();
        System.out.println("fun1 end");
    }

    public synchronized void fun2() {
        System.out.println("fun2 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun2 end");
    }

    /**
     * 结果:
     * fun1 start
     * fun2 start
     * fun2 end
     * fun1 end
     */
    public static void main(String[] args) {
        ReentrantSynchronizedLock1 r = new ReentrantSynchronizedLock1();
        new Thread(r::fun1).start();
    }
}
