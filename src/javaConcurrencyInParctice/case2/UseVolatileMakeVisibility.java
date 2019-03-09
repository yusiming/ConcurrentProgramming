package javaConcurrencyInParctice.case2;

/**
 * 使用volatile变量可以保证内存的可见性
 *
 * @author yusiming
 * @date 2019/3/9 11:32
 */
public class UseVolatileMakeVisibility {
    private volatile static boolean run = true;
    private volatile static int number = 0;

    private void fun() {
        while (run) {
            Thread.yield();
        }
        System.out.println("number: " + number);
    }

    /**
     * 使用volatile变量能够保证：
     * 1.主线程对于run和number变量的修改，对于t1线程是可见的
     * 2.对于使用volatile修饰的变量，编译器或者运行时都不会将对该变量操作与其他内存操作“重排序”
     * 3.对于使用volatile修饰的变量，不会被缓存在寄存器或者其他对处理器不可见的地方（多核处理器）
     */
    public static void main(String[] args) {
        UseVolatileMakeVisibility visibility = new UseVolatileMakeVisibility();
        Thread t1 = new Thread(visibility::fun);
        run = false;
        number = 10;
    }
}
