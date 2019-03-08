package javaConcurrencyInParctice.case1;

/**
 * 懒加载可能导致的线程安全问题
 *
 * @author yusiming
 * @date 2019/3/8 17:01
 */
public class LazyInitRaceCondition {
    private Object instance = null;

    public Object getInstance() {
        /*
         * 这里存在race condition（竞态条件）
         * 某个线程对于instance != null这个判断为true的结果可能会失效
         * 即另一个线程率先创建了实例
         */
        if (instance == null) {
            return new Object();
        }
        return instance;
    }
}
