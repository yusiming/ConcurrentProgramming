package javaConcurrencyInParctice.case4;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ThreadLocal可以提供一种作用域为“单个线程”的变量
 * 也可以实现线程封闭的特性，在JDBC中的Connection对象非常适合使用该技术
 * 连接池为每个线程分配一个Connection对象，线程使用完毕之后
 * 将该对象返回给连接池，并且在Connection对象返回之前
 * 连接池不会将Connection对象分配给别的线程使用
 *
 * @author yusiming
 * @date 2019/3/9 16:46
 */
public class JDBCUtil {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mmall?useSSL=false");
        dataSource.setUser("root");
        dataSource.setPassword("314159");
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        // 设置一些基本的池参数
        dataSource.setInitialPoolSize(10);
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(5);
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection == null) {
            connectionHolder.set(dataSource.getConnection());
            return connectionHolder.get();
        }
        return connection;
    }

    public static void returnConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection != null) {
            connection.close();
            connectionHolder.remove();
        }
    }

    public static void main(String[] args) throws SQLException {
        Thread t1 = new Thread(() -> {
            try {
                Connection c1 = getConnection();
                System.out.println(c1);
                System.out.println(c1 == getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Connection c2 = getConnection();
                System.out.println(c2);
                System.out.println(c2 == getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }
}
