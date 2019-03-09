package javaConcurrencyInParctice.case2;

/**
 * 如果对可变状态的访问，都在同一个锁上进行了同步
 * 那么就可以保证一个线程A对于变量的修改，对于另一个线程B是可见的
 *
 * @author yusiming
 * @date 2019/3/9 11:26
 */
public class MutableInteger4 {
    private int value;

    public int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }

    /**
     * 由于访问value值的set和get方法没有在同一个锁上进行同步
     * 所以不能保证一个线程掉set方法对于value值的修改，对于另一个线程调用get方法是可见的
     */
    public static void main(String[] args) {
        MutableInteger mutableInteger = new MutableInteger();
        Thread t1 = new Thread(() -> mutableInteger.setValue(10));
        t1.start();
        System.out.println(mutableInteger.getValue() == 10);
    }
}
