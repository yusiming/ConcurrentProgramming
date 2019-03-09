package javaConcurrencyInParctice.case2;

/**
 * 失效的数据
 *
 * @author yusiming
 * @date 2019/3/9 11:05
 */
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * t1线程对于value值的修改，对主线程有可能是不可见的
     */
    public static void main(String[] args) {
        MutableInteger mutableInteger = new MutableInteger();
        Thread t1 = new Thread(() -> mutableInteger.setValue(10));
        t1.start();
        System.out.println(mutableInteger.getValue() == 10);
    }
}
