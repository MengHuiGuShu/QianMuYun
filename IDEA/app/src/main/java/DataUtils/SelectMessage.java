package DataUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//继承Serializable方法，拓展Bundle功能
public class SelectMessage implements Serializable {

    private int num;
    private int n;

    private String title;
    private List<String> list = new ArrayList<>();
    private List<String> listmethon = new ArrayList<>();
    private List<String> listdose = new ArrayList<>();

    private List<String> stage = new ArrayList<>();


    public void setStage(List<String> stage) {
        this.stage = stage;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setListmethon(List<String> listmethon) {
        this.listmethon = listmethon;
    }

    public void setListdose(List<String> listdose) {
        this.listdose = listdose;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getList() {
        return list;
    }

    public List<String> getListdose() {
        return listdose;
    }

    public List<String> getListmethon() {
        return listmethon;
    }

    public int getNum() {
        return num;
    }

    public int getN() {
        return n;
    }

    public List<String> getStage() {
        return stage;
    }

    public void getAdd(String add){
        list.add(add);
        n += 1;
    }

    public void updataList(int list){
        this.list.set(list,"null");
    }
}
