package JDBCUtils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class MyDruid {

    private static DataSource dataSource = null;
    private static DruidDataSource druidDataSource = null;

    static {
        dataSource = new DruidDataSource();
        druidDataSource = (DruidDataSource) dataSource;
        try {
            druidDataSource.setUrl("jdbc:mysql://39.106.100.138:3306/study?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&useUnicode=yes");
            druidDataSource.setUsername("lab");
            druidDataSource.setPassword("123456789");
            druidDataSource.setMaxActive(10);
            druidDataSource.setMaxWait(3000);
            druidDataSource.setInitialSize(5);
            druidDataSource.setMinIdle(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Connection getConnect() throws Exception{
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void initConnect() throws Exception{
        Connection connection;
        connection = druidDataSource.getConnection();
        connection.close();

    }
}
