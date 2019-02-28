/**
 * 注意：
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 *
 * @Auther yusiming
 * @Date 2019/2/28 22:26
 */
public class ReferenceChange {
    private volatile Object o = new Object();

    public void fun() {
        synchronized (o) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReferenceChange c = new ReferenceChange();
        Thread t1 = new Thread(c::fun);
        t1.start();
        Thread.sleep(300);
        Thread t2 = new Thread(c::fun);
        t2.start();
        c.o = new Object();
        System.out.println(t2.getState());
    }
}
