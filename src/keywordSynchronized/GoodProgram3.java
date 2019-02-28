package keywordSynchronized;

/**
 * @Auther yusiming
 * @Date 2019/2/28 19:53
 */
public class GoodProgram3 {
    private int count = 10;

    /**
     * 如果将synchronized关键字直接加到普通方法的声明中
     * 就等同于方法执行时：synchronized(this)
     */
    private synchronized void increase() {
        count--;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }
}
