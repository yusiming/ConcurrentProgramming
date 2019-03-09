package javaConcurrencyInParctice.case3;

/**
 * 内部可变状态逸出(Escape)
 *
 * @author yusiming
 * @date 2019/3/9 16:01
 */
public class UnsafeStates {
    private String[] states = new String[]{"good", "bad"};

    /**
     * 通过一个共有的方法发布私有的状态
     * 对象本该只在内部使用，导致逸出了
     */
    public String[] getStates() {
        return states;
    }
}
