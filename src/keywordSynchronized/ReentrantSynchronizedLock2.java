package keywordSynchronized;

/**
 * 子类也可以调用父类的同步方法
 * 当子类继承父类的同步方法时，可以选择不使用synchronized关键字，
 * 也就是说，子类方法可以不用是同步方法
 *
 * @Auther yusiming
 * @Date 2019/2/28 21:07
 */
public class ReentrantSynchronizedLock2 {
    synchronized void fun() {
        System.out.println("父类方法开始: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("父类方法结束 " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        // 子类方法开始: 线程1
        // 父类方法开始: 线程1
        // 子类方法开始: 线程2
        // 父类方法结束 线程1
        // 子类方法结束: 线程1
        // 父类方法开始: 线程2
        // 父类方法结束 线程2
        // 子类方法结束: 线程2
        ReentrantSynchronizedLock22 tt = new ReentrantSynchronizedLock22();
        new Thread(tt::fun, "线程1").start();
        new Thread(tt::fun, "线程2").start();
        //子类方法开始: 线程3
        // 父类方法开始: 线程3
        // 父类方法结束 线程3
        // 子类方法结束: 线程3
        // 子类方法开始: 线程4
        // 父类方法开始: 线程4
        // 父类方法结束 线程4
        // 子类方法结束: 线程4
        Thread.sleep(6000);
        ReentrantSynchronizedLock222 ttt = new ReentrantSynchronizedLock222();
        new Thread(ttt::fun, "线程3").start();
        new Thread(ttt::fun, "线程4").start();
    }
}

class ReentrantSynchronizedLock22 extends ReentrantSynchronizedLock2 {
    @Override
    void fun() {
        System.out.println("子类方法开始: " + Thread.currentThread().getName());
        super.fun();
        System.out.println("子类方法结束: " + Thread.currentThread().getName());
    }
}

class ReentrantSynchronizedLock222 extends ReentrantSynchronizedLock2 {
    @Override
    synchronized void fun() {
        System.out.println("子类方法开始: " + Thread.currentThread().getName());
        super.fun();
        System.out.println("子类方法结束: " + Thread.currentThread().getName());
    }
}
