package JDBCUtils;

import ToolsUtils.*;

import java.sql.*;

import static ToolsUtils.StringUtils.trueOrfalse;

public class JDBC {
    private static String driver = "";
    private static String url = "";
    private static String user = "";
    private static String password = "";
    private static Connection connection;


    public static Connection Link(){
        try {
            connection = MyDruid.getConnect();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return connection;
        }
    }


    public static ResultSet executeSearch(PreparedStatement preparedStatement, String number) throws Exception {
        try {
            preparedStatement.setString(1,number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement.executeQuery();
    }


    public static int execteStoreDisinfectionRecords(PreparedStatement preparedStatement,DisinfectionRecords disinfectionRecords) throws SQLException {
        try{
            preparedStatement.setString(1,disinfectionRecords.getTime());
            preparedStatement.setString(2,disinfectionRecords.getName());
            preparedStatement.setString(3,disinfectionRecords.getMethod());
            preparedStatement.setString(4,"1");
            preparedStatement.setString(5,"1");
            preparedStatement.setString(6,disinfectionRecords.getEnclosure());
            preparedStatement.setString(7,disinfectionRecords.getDose());
        }catch (Exception e){
            e.printStackTrace();
        }
        return preparedStatement.executeUpdate();
    }



    public static int executeStorePedigreeMessage(PreparedStatement preparedStatement, PedigreeMessage pedigreeMessage) throws Exception {
        try {
            preparedStatement.setString(1,pedigreeMessage.getSex());
            preparedStatement.setString(2,pedigreeMessage.getAddress());
            preparedStatement.setString(3,pedigreeMessage.getBirthAddress());
            preparedStatement.setString(4,pedigreeMessage.getBirthday());
            preparedStatement.setString(5,pedigreeMessage.getBirthWeight());
            preparedStatement.setString(6,pedigreeMessage.getCoatColor());
            preparedStatement.setString(7,pedigreeMessage.getVariety());
            preparedStatement.setString(8,pedigreeMessage.getMother());
            preparedStatement.setString(9,pedigreeMessage.getFather());
            preparedStatement.setString(10,pedigreeMessage.getMotherfather());
            preparedStatement.setString(11,pedigreeMessage.getMothermother());
            preparedStatement.setString(12,pedigreeMessage.getFatherfather());
            preparedStatement.setString(13,pedigreeMessage.getFathermother());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement.executeUpdate();
    }

    public static int executeAddMedicineMessage(PreparedStatement preparedStatement, MedicineMessage medicineMessage) throws Exception {
        try {
            preparedStatement.setString(1,medicineMessage.getVaccinationMethod());
            preparedStatement.setString(2,medicineMessage.getDose());
            preparedStatement.setString(3,medicineMessage.getImmuneperiod());
            preparedStatement.setString(4,medicineMessage.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement.executeUpdate();
    }

    public static int executeImmunityMessageStore(PreparedStatement preparedStatement, MedicineMessage medicineMessage) throws Exception {
        try {
            preparedStatement.setString(1,medicineMessage.getTime());
            preparedStatement.setString(2,medicineMessage.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement.executeUpdate();
    }

    public static int executeDeleteMedicineMessage(PreparedStatement preparedStatement, String medicine) throws Exception {
        try {
            preparedStatement.setString(1,medicine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement.executeUpdate();
    }

    public static int executeUpdateMedicineMessage(PreparedStatement preparedStatement, MedicineMessage medicineMessage) throws Exception {
        try {
            preparedStatement.setString(1,medicineMessage.getVaccinationMethod());
            preparedStatement.setString(2,medicineMessage.getDose());
            preparedStatement.setString(3,medicineMessage.getImmuneperiod());
            preparedStatement.setString(4,medicineMessage.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement.executeUpdate();
    }

    public static int executeAnimalWefareMessage(PreparedStatement preparedStatement, WelfareMessage welfareMessage, String time) throws Exception {
        try {
            preparedStatement.setString(1,time);
            preparedStatement.setString(2,trueOrfalse(welfareMessage.getCzlcSQL()));
            preparedStatement.setString(3,trueOrfalse(welfareMessage.getSlkufwsSQL()));
            preparedStatement.setString(4,trueOrfalse(welfareMessage.getMsSQL()));
            preparedStatement.setString(5,trueOrfalse(welfareMessage.getYstfcgSQL()));
            preparedStatement.setString(6,trueOrfalse(welfareMessage.getYcwsSQL()));
            preparedStatement.setString(7,trueOrfalse(welfareMessage.getHjwsSQL()));
            preparedStatement.setString(8,trueOrfalse(welfareMessage.getScslsfmbSQL()));
            preparedStatement.setString(9,trueOrfalse(welfareMessage.getDjfhbnSQL()));
            preparedStatement.setString(10,trueOrfalse(welfareMessage.getMwSQL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement.executeUpdate();
    }


    //关闭连接，释放资源（preparedStatement, resultSet, connection）
    public static void close_link(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
