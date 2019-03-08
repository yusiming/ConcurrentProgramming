package javaConcurrencyInParctice.case1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * 一个非线程安全统计访问次数的servlet
 *
 * @author yusiming
 * @date 2019/3/8 16:50
 */
public class UnsafeCountingFactorizer extends HttpServlet {
    // count代表了对象的状态
    private long count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ++ 操作并非原子操作
        count++;
        BigInteger value = extractFromRequest(req);
        if (value != null) {
            BigInteger[] factors = factor(value);
            responseToClient(resp, factors);
        }
    }

    /**
     * 因式分解
     */
    private BigInteger[] factor(BigInteger value) {
        // 这里只是模拟
        return new BigInteger[100];
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

    /**
     * 给客户端响应
     */
    private void responseToClient(HttpServletResponse response, BigInteger[] values) {
        try (PrintWriter writer = response.getWriter()) {
            for (BigInteger bigInteger : values) {
                writer.write(bigInteger.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
