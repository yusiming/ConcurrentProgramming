package sleepMethod;

/**
 * sleep()方法不会释放锁，所以要小心了，
 * 如果某个线程带着锁永久的沉睡了，那么其他的线程永远都得不到执行机会
 *
 * @Auther yusiming
 * @Date 2019/2/28 20:09
 */
public class SleepWillNotReleaseTheLock {
    public synchronized void fun1() {
        System.out.println(Thread.currentThread().getName() + " 我进来了！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 我出去了");
    }

    /**
     * 执行结果：
     * Thread-0 我进来了！
     * Thread-0 我出去了
     * Thread-1 我进来了！
     * Thread-1 我出去了
     * <p>
     * 在Thread-0 sleep的5中秒中内，Thread-1无法进入方法
     */
    public static void main(String[] args) {
        SleepWillNotReleaseTheLock s = new SleepWillNotReleaseTheLock();
        new Thread(s::fun1).start();
        new Thread(s::fun1).start();
    }
}
