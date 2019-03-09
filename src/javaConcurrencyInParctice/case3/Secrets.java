package javaConcurrencyInParctice.case3;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布一个对象
 *
 * @author yusiming
 * @date 2019/3/9 15:58
 */
public class Secrets {
    // 通过一个静态属性将对象发布出去
    public static List<Secret> secretList;

    public void initialize() {
        secretList = new ArrayList<>();
    }
}

class Secret {

}
