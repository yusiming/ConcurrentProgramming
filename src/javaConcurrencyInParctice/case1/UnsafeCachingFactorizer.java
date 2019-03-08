package javaConcurrencyInParctice.case1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 当一个对象的状态，从无状态到添加了一个线程安全的状态（原子类变量啊）
 * 那么这个对象依然是线程安全的，因为这个状态变量是安全的
 * 但是当线程安全的状态从一个变为多个时，就不能保证整个复合操作的原子性
 *
 * @author yusiming
 * @date 2019/3/8 17:08
 */
public class UnsafeCachingFactorizer extends HttpServlet {
    AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigInteger value = extractFromRequest(req);
        if (value == null) {
            return;
        }
        // 这里存在竞态条件，判断value.equals(lastNumber.get())的结果可能会失效
        if (value.equals(lastNumber.get())) {
            responseToClient(resp, lastFactors.get());
        } else {
            // 这两个操作是一个复合操作，并不能保证原子性，尽管lastNumber和lastFactors是线程安全的
            lastNumber.set(value);
            lastFactors.set(factor(value));
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
