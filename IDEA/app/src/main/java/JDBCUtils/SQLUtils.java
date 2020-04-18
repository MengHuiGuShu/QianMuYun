package JDBCUtils;

public class SQLUtils {

    public static String SQLSearchSheepNumber(){
        return "select numberChar from sheepmessage where numberChar = ?";
    }

    public static String SQLAnimalWefareStore(){    //b'1'  b与'1'之间不能有空格！！！！！
        return "insert Animalwelfare( checkTime , czlc , slkfws , ms , ystfcg , ycws , hjws  , scslsfmb , djfhbn , mw) values( ? ,b? ,b? ,b? ,b? ,b? ,b? ,b? ,b? ,b? )";
    }

    public static String SQLPedigreeStore(){
        return "insert Pedigree( gender , farmAddress , birthAddress , birth , birthWeight , coatColor , variety , mother_id , father_id , mother_father , " +
                "mother_mother , father_father , father_mather)  values( b? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
    }

    public static String SQLSearchPedigreeMessage(){
        return "select * from Pedigree where _id = ?";
    }

    //在数据库中新增一种免疫药
    public static String SQLAddImmunityMedicine(){
        return "insert ImmunityMedicine(vaccinationMethod,dose,vaccinationTime,medicine) values( ? , ? , ? , ? )";
    }

    //在数据库中删除一种免疫药
    public static String SQLDeleteImmunityMedicine(){
        return "delete from ImmunityMedicine where medicine = ?";
    }

    //在数据库中查询一种免疫药
    public static String SQLSearchImmunityMedicine(){
        return "select * from ImmunityMedicine where medicine = ?";
    }

    //在数据库中修改一种免疫药的相关数据
    public static String SQLUpdateImmunityMedicine(){
        return "update ImmunityMedicine set vaccinationMethod = ? , dose = ? , vaccinationTime = ? whrer medicine = ?";
    }

    public static String SQLStoreImmunityMessage(){
        return "insert ImmunityMessage(TimeMessage,Medicine) values( ? , ? )";
    }
    //set names utf-8;


    public static String SQLSearchWelfareMessage(){
        return "select * from Animalwelfare";
    }

    public static String SQLStoreDisinfetionRecords(){
        return "insert 消毒记录(日期,消毒药,使用方法,生产工具,全场环境,圈舍,剂量) values( ? , ? , ? , b? , b? , ? , ? )";
    }
}
