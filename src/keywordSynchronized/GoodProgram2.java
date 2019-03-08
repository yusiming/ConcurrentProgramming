package keywordSynchronized;

/**
 * @author yusiming
 * @date 2019/2/28 19:51
 */
public class GoodProgram2 {
    private int count = 10;

    private void increase() {
        // 任何对象要执行下面的代码，就必须先拿到this，即当前对象的锁
        // 如果锁已经被某个线程持有了，那么当前的线程就必须等待
        // 直到持有锁的线程释放锁，才有机会执行
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + ": " + count);
        }
    }
}
