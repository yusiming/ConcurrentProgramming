package keywordSynchronized;

/**
 * @author yusiming
 * @date 2019/2/28 19:56
 */
public class GoodProgram4 {
    private static int count = 10;

    /**
     * 如果将synchronized关键字直接加到静态方法的声明中
     * 就等同于方法执行时：synchronized(GoodProgram.class)
     */
    private static synchronized void increase1() {
        count--;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }

    private static synchronized void increase2() {
        // 这里肯定是不能对 this 加锁的
        synchronized (GoodProgram4.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + ": " + count);
        }
    }
}
