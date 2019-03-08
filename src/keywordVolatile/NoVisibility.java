package keywordVolatile;

/**
 * 有关内存可见性的另一个例子
 *
 * @author yusiming
 * @date 2019/3/2 09:25
 */
public class NoVisibility {
    private static int number;
    private static boolean ready;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        });
        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
         * 这里可能存在指令的重排序，有可能t1先“看到了”ready值的修改
         * 就直接跳出了循环，打印number的值，而number的值t1并没有看到主线程对他的修改
         * 所以打印时，有可能会打印number的值为默认值0
         * 也有可能两个值的修改t1都没有看到，t1就会一直循环运行下去
         * 也就是说，当主线线程在自己的工作内存中修改了number和ready的值时，
         * 不能保证这个修改对其他线程是可见的，其他线程有可能使用的还是自己工作内存中的值
         * 这就叫做内存不可见
         */
        number = 47;
        ready = true;
    }
}
