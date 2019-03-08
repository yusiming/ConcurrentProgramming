package keywordSynchronized;

/**
 * 默认出现异常将会释放锁
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据。
 * 因此要非常小心的处理同步业务逻辑中的异常，出现异常时需要做一些处理，比如事物回滚
 *
 * @author yusiming
 * @date 2019/2/28 21:24
 */
public class ExceptionWillReleaseLock {
    private int count = 0;

    private synchronized void fun() {
        while (true) {
            count++;
            System.out.println("count = " + count + ": " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                // 如果不希望释放锁，需要在这里捕获异常，让循环继续
                int i = count / 0;
            }
        }
    }

    /**
     * 结果：
     * count = 1: 线程1
     * count = 2: 线程1
     * count = 3: 线程1
     * count = 4: 线程1
     * count = 5: 线程1
     * count = 6: 线程2
     * Exception in thread "线程1" java.lang.ArithmeticException: / by zero
     * at keywordSynchronized.ExceptionWillReleaseLock.fun(ExceptionWillReleaseLock.java:26)
     * at java.lang.Thread.run(Thread.java:748)
     * count = 7: 线程2
     * <p>
     * 当发生异常，线程将锁释放之后，就可以执行方法了
     */
    public static void main(String[] args) {
        ExceptionWillReleaseLock e = new ExceptionWillReleaseLock();
        new Thread(e::fun, "线程1").start();
        new Thread(e::fun, "线程2").start();
    }
}
