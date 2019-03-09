package javaConcurrencyInParctice.case2;

/**
 * 如果对某一个共享可变的状态，没有使用同一个锁来进行同步
 * 那么不能保证线程A 获取 锁o1之后对于状态的修改，
 * 能够对线程B 获取锁o2 之后可见
 *
 * @author yusiming
 * @date 2019/3/9 11:15
 */
public class MutableInteger2 {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }

    /**
     * t1线程对于value值的修改对于主线程是可见的
     * 因为对于变量value的操作都在同一个锁上进行了同步
     */
    public static void main(String[] args) {
        MutableInteger mutableInteger = new MutableInteger();
        Thread t1 = new Thread(() -> mutableInteger.setValue(10));
        t1.start();
        System.out.println(mutableInteger.getValue() == 10);
    }
}
