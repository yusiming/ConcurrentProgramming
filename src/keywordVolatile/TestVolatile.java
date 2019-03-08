package keywordVolatile;

/**
 * volatile关键字能够保证可见性：
 * 即一个线程修改了一个被volatile修饰的变量的值，其他线程都可以立即知道
 *
 * @author yusiming
 * @date 2019/2/28 21:37
 */
public class TestVolatile {
    private /*volatile*/ boolean running = true;
    // 也可以修饰引用类型
    // private volatile Object object = new Object();

    public void fun() {
        while (running) {
            //
        }
    }

    public static void main(String[] args) {
        TestVolatile t = new TestVolatile();
        new Thread(t::fun).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
