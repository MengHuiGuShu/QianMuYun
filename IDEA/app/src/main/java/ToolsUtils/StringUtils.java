package ToolsUtils;

public class StringUtils {
    //判断EdiText格式是否符合标准
    public static boolean format(String editText){
        return true;
    }

    //将true or false转换为'1' or '0'
    public static String trueOrfalse(boolean result){
        if(result){
            return "1";
        }else {
            return "0";
        }
    }

    //将true or false转换为'是' or '否'
    public static String trueOrfalsePlus(boolean result){
        if(result){
            return "是";
        }else {
            return "否";
        }
    }
}
