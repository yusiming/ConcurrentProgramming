package javaConcurrencyInParctice.case2;

/**
 * 如果只对set方法进行同步
 * get方法获取的也有可能是失效的
 * 或者两次get方法获取的不是同一个值（脏读）
 *
 * @author yusiming
 * @date 2019/3/9 11:22
 */
public class MutableInteger3 {
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
