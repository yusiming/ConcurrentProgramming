package javaConcurrencyInParctice;

/**
 * @author yusiming
 * @date 2019/3/8 13:45
 */
public class Sequence {
    private int value;

    /**
     * 非同步方法，有可能会引发多线程执行时的问题：
     * 比如：
     * 两个线程获取到相同的值，线程A执行该方法，线程B执行该方法，获取到相同的值
     * 如果这个方法是为了生成一个唯一的序列值就会产生问题
     */
    public int getValue() {
        return value++;
    }

    /**
     * 同步方法，任何线程如果需要执行这个方法，首先需要获得这个对象的内置锁
     * 当两个线程执行这个方法时，如果线程A获得了内置锁，线程A就可以执行这个方法
     * 但是如果线程B在线程A执行方法的期间，也想要执行这个方法，那么就会阻塞
     * 直到线程释放内置锁，无论是方法正常退出，还是抛出异常退出，或者通过object中的wait方法释放锁
     */
    public synchronized int getNext() {
        return value++;
    }
}
