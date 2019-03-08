package keywordVolatile;

/**
 * 使用synchronized关键字来保证原子性
 *
 * @author yusiming
 * @date 2019/2/28 22:18
 */
public class TestVolatile3 {
    private volatile int count = 0;

    private synchronized void increase() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println(count);
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
        TestVolatile3 t = new TestVolatile3();
        for (int i = 0; i < 10; i++) {
            new Thread(t::increase).start();
        }
    }

}
