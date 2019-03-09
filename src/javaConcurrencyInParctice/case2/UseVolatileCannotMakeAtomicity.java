package javaConcurrencyInParctice.case2;

/**
 * 使用volatile修改的变量，并不能保证对于该变量的操作是原子性的
 *
 * @author yusiming
 * @date 2019/3/9 11:39
 */
public class UseVolatileCannotMakeAtomicity {
    private static int count = 0;

    public static void add() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(UseVolatileCannotMakeAtomicity::add).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 总是一个小于100000的值
        System.out.println(count);
    }
}
