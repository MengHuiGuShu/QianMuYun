package ToolsUtils;

import java.util.Objects;

public class DisinfectionRecords {
    private String name;
    private String method;
    private String dose;
    private String time;
    private String enclosure;
    private String allEnvironment;
    private boolean allFlag;
    private boolean toolsFlag;
    private int check;


    public int CheckMessage() {
        check = 0;
        if(name == null){
            check += 1;
        }
        if(method == null){
            check += 1;
        }
        if(dose == null){
            check += 1;
        }
        if(time == null){
            check += 1;
        }
        if(Objects.equals(enclosure, "")){
            check += 1;
        }
        return check;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setAllEnvironment(String allEnvironment) {
        this.allEnvironment = allEnvironment;
    }

    public void setAllFlag(boolean allFlag) {
        this.allFlag = allFlag;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setToolsFlag(boolean toolsFlag) {
        this.toolsFlag = toolsFlag;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }

    public String getAllEnvironment() {
        return allEnvironment;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public String getMethod() {
        return method;
    }
}
