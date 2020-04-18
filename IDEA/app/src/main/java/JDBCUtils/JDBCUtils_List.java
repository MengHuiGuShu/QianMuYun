package JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ToolsUtils.WelfareMessage;

import static JDBCUtils.JDBC.Link;
import static JDBCUtils.SQLUtils.SQLSearchWelfareMessage;


public class JDBCUtils_List {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    public List<WelfareMessage> readSQL(){
        List<WelfareMessage> list = new ArrayList<>();
        connection = Link();
        try {
            preparedStatement = connection.prepareStatement(SQLSearchWelfareMessage());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                WelfareMessage welfareMessage = new WelfareMessage(
                        resultSet.getString("checkTime"),
                        resultSet.getBoolean("czlc"),
                        resultSet.getBoolean("slkfws"),
                        resultSet.getBoolean("ms"),
                        resultSet.getBoolean("ystfcg"),
                        resultSet.getBoolean("ycws"),
                        resultSet.getBoolean("hjws"),
                        resultSet.getBoolean("scslsfmb"),
                        resultSet.getBoolean("djfhbn"),
                        resultSet.getBoolean("mw"));
                list.add(welfareMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
