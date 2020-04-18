package ToolsUtils;


import android.os.Parcel;
import android.os.Parcelable;

public class WelfareMessage {
    private boolean czlc;           //操作流程是否规范
    private boolean slkfws;         //饲料库房卫生
    private boolean ms;             //灭鼠
    private boolean ystfcg;         //羊舍通风采光
    private boolean ycws;           //羊床卫生
    private boolean hjws;           //环境卫生
    private boolean scslsfmb;       //饲草饲料是否霉变
    private boolean djfhbn;         //冬季防寒保暖
    private boolean mw;             //灭蚊

    private boolean czlcflag = false;           //操作流程是否规范
    private boolean slkfwsflag = false;         //饲料库房卫生
    private boolean msflag = false;             //灭鼠
    private boolean ystfcgflag = false;         //羊舍通风采光
    private boolean ycwsflag = false;           //羊床卫生
    private boolean hjwsflag = false;           //环境卫生
    private boolean scslsfmbflag = false;       //饲草饲料是否霉变
    private boolean djfhbnflag = false;         //冬季防寒保暖
    private boolean mwflag = false;             //灭蚊

    private String date = "";                   //检查时间


    public WelfareMessage(String data, boolean czlc,boolean slkfws,boolean ms,boolean ystfcg,boolean ycws,boolean hjws,boolean scslsfmb,boolean djfhbn, boolean mw){
        this.date = data;
        this.czlc = czlc;
        this.djfhbn= djfhbn;
        this.hjws = hjws;
        this.ms = ms;
        this.mw = mw;
        this.scslsfmb = scslsfmb;
        this.slkfws = slkfws;
        this.ycws = ycws;
        this.ystfcg = ystfcg;
    }


    public void flagczlc(){
        this.czlc = true;
    }
    public void flagslkfws(){
        this.slkfws = true;
    }
    public void flagms(){
        this.ms = true;
    }
    public void flagystftg(){
        this.ystfcg = true;
    }
    public void flagycws(){
        this.ycws = true;
    }
    public void flaghjws(){
        this.hjws = true;
    }
    public void flagdjfhbn(){
        this.djfhbn = true;
    }
    public void flagmw(){
        this.mw = true;
    }
    public void flagscslsfmb(){
        this.scslsfmb = true;
    }

    public void unflagscslsfmb(){
        this.scslsfmb = false;
    }
    public void unflagczlc(){
        this.czlc = false;
    }
    public void unflagslkfws(){
        this.slkfws = false;
    }
    public void unflagms(){
        this.ms = false;
    }
    public void unflagystftg(){
        this.ystfcg = false;
    }
    public void unflagycws(){
        this.ycws = false;
    }
    public void unflaghjws(){
        this.hjws = false;
    }
    public void unflagdjfhbn(){
        this.djfhbn = false;
    }
    public void unflagmw(){
        this.mw = false;
    }

    public void setCzlc(){
        this.czlcflag = true;
    }
    public void setSlkfws(){
        this.slkfwsflag = true;
    }
    public void setYstfcg(){
        this.ystfcgflag = true;
    }
    public void setMs(){
        this.msflag = true;
    }
    public void setYcws(){
        this.ycwsflag = true;
    }
    public void setHjws(){
        this.hjwsflag = true;
    }
    public void setScslsfmb(){
        this.scslsfmbflag = true;
    }
    public void setDjfhbn(){
        this.djfhbnflag = true;
    }
    public void setMw(){
        this.mwflag = true;
    }

    public boolean getCzlc(){
        return this.czlcflag;
    }
    public boolean getSlkufws(){
        return this.slkfwsflag;
    }
    public boolean getMw(){
        return this.mwflag;
    }
    public boolean getMs(){
        return this.msflag;
    }
    public boolean getScslsfmb(){
        return scslsfmbflag;
    }
    public boolean getDjfhbn(){
        return this.djfhbnflag;
    }
    public boolean getYcws(){
        return this.ycwsflag;
    }
    public boolean getYstfcg(){
        return this.ystfcgflag;
    }
    public boolean getHjws(){
        return this.hjwsflag;
    }

    public boolean getAllFlag(){
        return czlcflag && slkfwsflag && mwflag && msflag && ycwsflag && ystfcgflag && hjwsflag && scslsfmbflag && djfhbnflag;
    }

    public boolean getCzlcSQL(){
        return this.czlc;
    }
    public boolean getSlkufwsSQL(){
        return this.slkfws;
    }
    public boolean getMwSQL(){
        return this.mw;
    }
    public boolean getMsSQL(){
        return this.ms;
    }
    public boolean getScslsfmbSQL(){
        return scslsfmb;
    }
    public boolean getDjfhbnSQL(){
        return this.djfhbn;
    }
    public boolean getYcwsSQL(){
        return this.ycws;
    }
    public boolean getYstfcgSQL(){
        return this.ystfcg;
    }
    public boolean getHjwsSQL(){
        return this.hjws;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setCzlc(boolean czlc) {
        this.czlc = czlc;
    }

    public void setDjfhbn(boolean djfhbn) {
        this.djfhbn = djfhbn;
    }

    public void setHjws(boolean hjws) {
        this.hjws = hjws;
    }

    public void setMs(boolean ms) {
        this.ms = ms;
    }

    public void setMw(boolean mw) {
        this.mw = mw;
    }

    public void setScslsfmb(boolean scslsfmb) {
        this.scslsfmb = scslsfmb;
    }

    public void setSlkfws(boolean slkfws) {
        this.slkfws = slkfws;
    }

    public void setYcws(boolean ycws) {
        this.ycws = ycws;
    }

    public void setYstfcg(boolean ystfcg) {
        this.ystfcg = ystfcg;
    }

}
