/**
 * 不要使字符串常量作为锁定对象，
 * 这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符串“Hello”，
 * 如果你在自己的代码中也锁定了"Hello",这时候就有可能发生非常诡异的死锁阻塞，
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
 *
 * @author yusiming
 * @date 2019/2/28 22:40
 */
public class DoNotUseStringForLock {
    private String s1 = "hello";
    private String s2 = "hello";

    // fun1()和fun2() 锁定的是同一个对象
    public void fun1() {
        synchronized (s1) {

        }
    }

    public void fun2() {
        synchronized (s2) {

        }
    }
}
