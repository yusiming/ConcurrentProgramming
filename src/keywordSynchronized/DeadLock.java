package keywordSynchronized;

/**
 * 模拟死锁
 *
 * @author yusiming
 * @date 2019/2/28 20:59
 */
public class DeadLock {
    private final Object o1 = new Object();
    private final Object o2 = new Object();

    public void fun1() {
        synchronized (o1) {
            try {
                // 等一会线程2
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fun2();
        }
    }

    public void fun2() {
        synchronized (o2) {
            fun1();
        }
    }

    /**
     * 两个线程都会堵塞:
     * 线程1持有o1，当访问方法2时，发现o2已经被锁定
     * 线程2持有o2，当访问方法1时，发现o1已经被锁定
     * 两个线程谁也不让谁，都想要对方的东西，这就造成了死锁
     */
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::fun1).start();
        new Thread(deadLock::fun2).start();
    }
}
