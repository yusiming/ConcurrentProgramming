package keywordSynchronized;

/**
 * 同步方法和非同步方法可以同时调用，
 * 调用非同步方法时不需要得到某个堆中的对象的锁，直接调用
 *
 * @Auther yusiming
 * @Date 2019/2/28 20:02
 */
public class TwoKindOfMethod {
    public synchronized void fun1() {
        System.out.println("fun1 start");
        try {
            // 注意这里不会释放持有的锁
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun1 end");
    }

    public void fun2() {
        System.out.println("fun2 start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun2 end");
    }

    /**
     * fun1 start
     * fun2 start
     * fun2 end
     * fun1 end
     */
    public static void main(String[] args) {
        TwoKindOfMethod t = new TwoKindOfMethod();
        new Thread(t::fun1).start();
        new Thread(t::fun2).start();
    }
}
