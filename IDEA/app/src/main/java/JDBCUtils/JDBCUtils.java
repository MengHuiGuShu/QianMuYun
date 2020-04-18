package JDBCUtils;

import DataUtils.SelectMessage;
import ToolsUtils.DisinfectionRecords;
import ToolsUtils.MedicineMessage;
import ToolsUtils.PedigreeMessage;
import ToolsUtils.WelfareMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static JDBCUtils.JDBC.*;
import static JDBCUtils.SQLUtils.*;

public class JDBCUtils {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;







    public int StoreDisinfectionRecords(DisinfectionRecords disinfectionRecords){
        try{
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLStoreDisinfetionRecords());
            return execteStoreDisinfectionRecords(preparedStatement,disinfectionRecords);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }




    public int SearchSheepNumber(String Number){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLSearchPedigreeMessage());
            resultSet = executeSearch(preparedStatement,Number);
            if(resultSet.next()){
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public SelectMessage SearchStageMessage(Connection connection,String SQLstatement){
        SelectMessage selectMessage = new SelectMessage();
        List<String> list = new ArrayList<>();
        int n = 0;
        try{
            preparedStatement = connection.prepareStatement("select * from " + SQLstatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("stage"));
                n += 1;
            }
            selectMessage.setList(list);
            selectMessage.setN(n);
            return selectMessage;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public SelectMessage SearchTableMessage(Connection connection,String SQLstatement){
        SelectMessage selectMessage = new SelectMessage();
        List<String> list = new ArrayList<>();
        List<String> listmethon = new ArrayList<>();
        List<String> listdose = new ArrayList<>();
        int n = 0;
        try{
            preparedStatement = connection.prepareStatement("select * from " + SQLstatement);
            resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
                listmethon.add(resultSet.getString("methon"));
                listdose.add(resultSet.getString("dose"));
                n += 1;
            }
            selectMessage.setN(n);
            selectMessage.setList(list);
            selectMessage.setListmethon(listmethon);
            selectMessage.setListdose(listdose);
            return selectMessage;
        }catch (Exception e){
            e.printStackTrace();
            return selectMessage;
        }finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public List<WelfareMessage> SearchWelfareMessage(){
        List<WelfareMessage> WelfareMessageList = new ArrayList<>();
        WelfareMessage[] welfareMessage = new WelfareMessage[100];          //暂时只支持导出一百条信息
        for(int i = 0; i < 100; i += 1){                                    //类数组需要这样实例化才能运行
            welfareMessage[i] = new WelfareMessage("",false,false,false,false,
                    false,false,false,false,false);
        }
        int i = 0;
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLSearchWelfareMessage());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                welfareMessage[i].setDate(resultSet.getString("checkTime"));
                welfareMessage[i].setYstfcg(resultSet.getBoolean("ystfcg"));
                welfareMessage[i].setScslsfmb(resultSet.getBoolean("scslsfmb"));
                welfareMessage[i].setSlkfws(resultSet.getBoolean("slkfws"));
                welfareMessage[i].setYcws(resultSet.getBoolean("ycws"));
                welfareMessage[i].setMs(resultSet.getBoolean("ms"));
                welfareMessage[i].setHjws(resultSet.getBoolean("hjws"));
                welfareMessage[i].setCzlc(resultSet.getBoolean("czlc"));
                welfareMessage[i].setMw(resultSet.getBoolean("mw"));
                welfareMessage[i].setDjfhbn(resultSet.getBoolean("djfhbn"));
                WelfareMessageList.add(welfareMessage[i]);
                i += 1;
            }
            return WelfareMessageList;
        } catch (Exception e) {
            e.printStackTrace();
            WelfareMessageList = null;
            return WelfareMessageList;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean SearchMedicineMessage(MedicineMessage medicineMessage){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLSearchImmunityMedicine());
            resultSet = executeSearch(preparedStatement,medicineMessage.getName());
            boolean result = resultSet.next();
            if(result){
                medicineMessage.setDose(resultSet.getString("dose"));
                medicineMessage.setImmuneperiod(resultSet.getString("vaccinationTime"));
                medicineMessage.setVaccinationMethod(resultSet.getString("vaccinationMethod"));
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean ImmunityMessageStore(MedicineMessage medicineMessage){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLStoreImmunityMessage());
            return executeImmunityMessageStore(preparedStatement,medicineMessage) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean AddMedicineMessage(MedicineMessage medicineMessage){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLAddImmunityMedicine());
            return executeAddMedicineMessage(preparedStatement,medicineMessage) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean DeleteMedicineMessage(MedicineMessage medicineMessage){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLDeleteImmunityMedicine());
            return executeDeleteMedicineMessage(preparedStatement,medicineMessage.getName()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean UpdateMedicineMessage(MedicineMessage medicineMessage){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLUpdateImmunityMedicine());
            return executeUpdateMedicineMessage(preparedStatement,medicineMessage) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean storeAnimalWefareMessage(WelfareMessage welfareMessage,String time){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLAnimalWefareStore());
            return executeAnimalWefareMessage(preparedStatement, welfareMessage, time) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean SearchPedigreeMessage(String number, PedigreeMessage pedigreeMessage){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLSearchPedigreeMessage());
            resultSet = executeSearch(preparedStatement,number);
            boolean result = resultSet.next();
            if(result){
                pedigreeMessage.setSex(resultSet.getString("gender"));;
                pedigreeMessage.setAddress(resultSet.getString("farmAddress"));
                pedigreeMessage.setBirthAddress(resultSet.getString("birthAddress"));
                pedigreeMessage.setBirthday(resultSet.getString("birth"));
                pedigreeMessage.setBirthWeight(resultSet.getString("birthWeight"));
                pedigreeMessage.setCoatColor(resultSet.getString("coatColor"));
                pedigreeMessage.setVariety(resultSet.getString("variety"));
                pedigreeMessage.setMother(resultSet.getString("mother_id"));
                pedigreeMessage.setFather(resultSet.getString("father_id"));
                pedigreeMessage.setMotherfather(resultSet.getString("mother_father"));
                pedigreeMessage.setMothermother(resultSet.getString("mother_mother"));
                pedigreeMessage.setFatherfather(resultSet.getString("father_father"));
                pedigreeMessage.setFathermother(resultSet.getString("father_mather"));
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }

    public boolean storePedigreeMessage(PedigreeMessage pedigreeMessage){
        try {
            connection = Link();
            preparedStatement = connection.prepareStatement(SQLPedigreeStore());
            return executeStorePedigreeMessage(preparedStatement,pedigreeMessage) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close_link(preparedStatement,resultSet,connection);
        }
    }
}
