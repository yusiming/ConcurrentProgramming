package javaConcurrencyInParctice.case2;

/**
 * 使用锁或者原子变量保证原子性和内存可见性
 *
 * @author yusiming
 * @date 2019/3/9 15:06
 */
public class UseSynchronizerMakeAtomicity {
    private static int count = 0;
    // private static AtomicInteger count = new AtomicInteger(0);

    public /*synchronized*/ static void add() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(UseSynchronizerMakeAtomicity::add).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 总是一个小于100000的值
        System.out.println(count);
    }

}
