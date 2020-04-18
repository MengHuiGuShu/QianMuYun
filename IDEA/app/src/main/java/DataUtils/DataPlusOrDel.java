package DataUtils;

import java.util.ArrayList;
import java.util.List;

public class DataPlusOrDel {
    private List<String> listPlus = new ArrayList<>();
    private List<String> listDel = new ArrayList<>();

    public void setListDel(String Delete) {
        listDel.add(Delete);
    }

    public void setListPlus(String Plus) {
        listPlus.add(Plus);
    }

    public List<String> getListDel() {
        return listDel;
    }

    public List<String> getListPlus() {
        return listPlus;
    }

    public int getPlusNum() {
        return listPlus.size();
    }

    public int getDelNum() {
        return listDel.size();
    }
}
