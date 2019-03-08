package javaConcurrencyInParctice.case1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 无状态的servlet，是线程安全的
 * 所有的状态（变量）都是局部的
 * 只会被线程自己使用，都只会
 * 存储到线程私有的局部变列表中
 *
 * @author yusiming
 * @date 2019/3/8 16:38
 */
public class StatelessFactorizer extends HttpServlet {
    /**
     * 提供因式分解的服务的get方法
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameter("number");
        BigInteger bigInteger = extractFromRequest(request);
        if (bigInteger != null) {
            factor(bigInteger);
        }
    }

    /**
     * 因式分解
     */
    private void factor(BigInteger value) {
        // 这里只是模拟
    }

    /**
     * 从请求中提取需要因式分解的数字
     */
    private BigInteger extractFromRequest(HttpServletRequest request) {
        String number = request.getParameter("number");
        if (number != null) {
            return new BigInteger(number);
        }
        return null;
    }
}
