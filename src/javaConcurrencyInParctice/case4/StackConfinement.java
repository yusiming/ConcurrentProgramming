package javaConcurrencyInParctice.case4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * 线程封闭之“栈封闭”
 * 下面的一个servlet程序就是一个经典的栈封闭的案例
 * 而我们在进行web开发的时候，使用的mvc模式中，
 * 也大量使用了栈封闭，来保证了线程安全性
 * 注意：对于在方法中声明的基本类型，是永远不会破坏栈封闭的
 * 因为无法获得基本类型的数据的引用，但是对于引用类型的数据
 * 就不同了，我们需要防止引用类型的数据被发布到错误的地方，
 * 否则会破化栈封闭
 *
 * @author yusiming
 * @date 2019/3/9 16:39
 */
public class StackConfinement extends HttpServlet {

    /**
     * 提供因式分解的服务的get方法
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameter("number");
        BigInteger bigInteger = extractFromRequest(request);
        if (bigInteger != null) {
            BigInteger[] factors = factor(bigInteger);
            responseToClient(response, factors);
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
