package javaConcurrencyInParctice.case2;

/**
 * 当多个线程同时对一个共享并且可变的状态进行操作
 * 如果没有加上适当的同步机制，那么一个线程A对于变量的修改
 * 另一个线程B可能会“看不到”，或者说线程B使用了失效的数据。
 *
 * @author yusiming
 * @date 2019/3/9 10:50
 */
public class MemoryNoVisibility {
    private static int number = 0;
    private static boolean run = true;

    public void fun() {
        while (run) {
            Thread.yield();
        }
        System.out.println(number);
    }

    /**
     * 如果没有使用同步机制，那么主线程对变量 run 和 number 的修改
     * 另一个线程有可能“看不到”，或者看到了对变量run 的修改 ，
     * 但是没有看到对变量number的修改
     * 而且Java内存模型允许，在编译时、运行时对指令进行重新排序，并将数值缓存在寄存器中
     * 以获得更好的执行效率
     * 也就是说有可能先对run 变量进行了修改，然后再去修改number的值
     * 所以另一个线程的执行就可能存在多种情况：
     * 1.没有看到主线对于number和run的修改，永远执行下去
     * 2.看到了主线程对于number的修改，没有看到对于run的修改，也会远执行下去
     * 3.看到了主线程对于run的修改，没有看到对于number的修改，所以会输出 0
     * 4.看到了主线程对于run和number的修改，所以会输出 10
     */
    public static void main(String[] args) {
        MemoryNoVisibility noVisibility = new MemoryNoVisibility();
        new Thread(noVisibility::fun).start();
        number = 10;
        run = false;
    }
}
