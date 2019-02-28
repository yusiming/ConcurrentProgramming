package waitMethod;

/**
 * Object中的wait方法会释放当前锁
 *
 * @Auther yusiming
 * @Date 2019/2/28 20:27
 */
public class WaitMethodWillReleaseTheLock {
    public synchronized void fun1() {
        System.out.println(Thread.currentThread().getName() + " 我进来了！");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 我出去了");
    }

    public synchronized void fun2() {
        System.out.println(Thread.currentThread().getName() + " 我进来了！");
        try {
            this.wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 我出去了");
    }

    /**
     * 执行结果：
     * fun1():两个线程永久无法结束
     * Thread-0 我进来了！
     * Thread-1 我进来了！
     * <p>
     * fun2():
     * Thread-0 我进来了！
     * Thread-1 我进来了！
     * Thread-0 我出去了
     * Thread-1 我出去了
     */
    public static void main(String[] args) {
        WaitMethodWillReleaseTheLock w = new WaitMethodWillReleaseTheLock();
        new Thread(w::fun2).start();
        new Thread(w::fun2).start();
    }
}
