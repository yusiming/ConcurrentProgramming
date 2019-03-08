package javaConcurrencyInParctice.case1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * 使用synchronized关键字来保证多个可变状态变量同时被更新
 * 细粒度的同步代码块可以很好的在性能和线程安全之间找到一个平衡
 *
 * @author yusiming
 * @date 2019/3/8 17:33
 */
public class SynchronizedFactorizer extends HttpServlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cachaHist;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigInteger value = extractFromRequest(req);
        if (value == null) {
            return;
        }
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            BigInteger number = extractFromRequest(req);
            if (number != null && number.equals(lastNumber)) {
                cachaHist++;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(value);
            synchronized (this) {
                lastNumber = value;
                lastFactors = factors;
            }
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
