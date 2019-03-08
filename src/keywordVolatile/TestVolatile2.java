package keywordVolatile;

/**
 * volatile关键字不能保证原子性
 *
 * @author yusiming
 * @date 2019/2/28 22:03
 */
public class TestVolatile2 {
    private volatile int count = 0;

    private void increase() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println(count);
    }

    /**
     * 结果：
     * 13361
     * 16205
     * 26205
     * 36205
     * 46205
     * 56205
     * 66205
     * 76462
     * 85656
     * 95656
     */
    public static void main(String[] args) {
        TestVolatile2 t = new TestVolatile2();
        for (int i = 0; i < 10; i++) {
            new Thread(t::increase).start();
        }
    }

}
