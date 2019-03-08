package keywordVolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类，即一系列的AtomXXX类也可以保证原子性
 * 而且一般情况下效率会更高
 *
 * @author yusiming
 * @date 2019/2/28 22:21
 */
public class TestVolatile4 {
    private AtomicInteger count = new AtomicInteger(0);

    private synchronized void increase() {
        for (int i = 0; i < 10000; i++) {
            // 原子性操作
            count.incrementAndGet();
        }
        System.out.println(count.get());
    }

    /**
     * 结果：
     * 10000
     * 20000
     * 30000
     * 40000
     * 50000
     * 60000
     * 70000
     * 80000
     * 90000
     * 100000
     */
    public static void main(String[] args) {
        TestVolatile4 t = new TestVolatile4();
        for (int i = 0; i < 10; i++) {
            new Thread(t::increase).start();
        }
    }

}
