package keywordSynchronized;

/**
 * 多线程带来的问题：线程之前竞争临界资源时，如果不加以控制，就会有可能造成错误
 *
 * @Auther yusiming
 * @Date 2019/2/28 19:27
 */
public class BadProgram {
    private int count = 10;

    private void increase() {
        count--;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }

    /**
     * 运行结果之一：
     * Thread-0: 8
     * Thread-1: 8
     * Thread-2: 7
     * Thread-3: 6
     * Thread-4: 5
     * <p>
     * 为什么会出现上面的运行结果：
     * 这是由于increase()方法并不是一个同步方法，
     * 一个线程在该方法运行的任何时候都有可能被剥夺执行权限，转而去执行另一个线程
     * 所以就可能导致了，线程1将count-1之后，cpu又被分配给线程2了，此时线程2也将count-1
     * 然后打印结果，线程2此时就结束了，然后cpu又被分配给线程1，此时继续从中断位置运行
     * 但此时count的值，已经不是9了，而是8了，这也是为什么会打印两次8 的原因
     * 注意：即使是count++ 操作，也不是原子操作
     */
    public static void main(String[] args) {
        BadProgram bd = new BadProgram();
        for (int i = 0; i < 5; i++) {
            new Thread(bd::increase).start();
        }
    }
}
