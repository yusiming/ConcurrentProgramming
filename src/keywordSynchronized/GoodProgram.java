package keywordSynchronized;

/**
 * 使用synchronized关键字来保证某一段代码的执行的原子性和可见性
 *
 * @Auther yusiming
 * @Date 2019/2/28 19:42
 */
public class GoodProgram {
    private int count = 10;
    private Object o = new Object();

    private void increase() {
        // 注意这里并不是对代码进行加锁了，而是锁定了堆中的某一个对象，
        // 如果某个线程需要执行synchronized代码中的代码，就必须到堆中
        // 去查看这个对象是否被锁定，如果这个对象已经被某个线程锁定了
        // 那么当前的线程就只能等待，直到锁被释放
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + ": " + count);
        }
    }

    public static void main(String[] args) {
        GoodProgram gd = new GoodProgram();
        for (int i = 0; i < 5; i++) {
            new Thread(gd::increase).start();
        }
    }
}
