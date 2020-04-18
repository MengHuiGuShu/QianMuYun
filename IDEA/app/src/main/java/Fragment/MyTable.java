package Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.example.qianmuyun.R;

import java.util.ArrayList;
import java.util.List;

import ToolsUtils.WelfareMessage;

public class MyTable extends Fragment {

    private Activity activity;
    private View view;
    private SmartTable smartTable;
    private List<WelfareMessage> list = new ArrayList<>();
    private int page;
    private boolean flag = false;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mytable,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        if (flag){
            initTable();
        }
    }

    private void initTable() {
        Column<String> date = new Column<>("时间", "date");
        Column<Boolean> czlc = new Column<>("操作流程是否规范", "czlc");
        Column<Boolean> slkfws = new Column<>("饲料库房卫生", "slkfws");
        Column<Boolean> ms = new Column<>("灭鼠", "ms");
        Column<Boolean> ystfcg = new Column<>("羊舍通风采光", "ystfcg");
        Column<Boolean> ycws = new Column<>("羊床卫生", "ycws");
        Column<Boolean> hjws = new Column<>("环境卫生", "hjws");
        Column<Boolean> scslsfmb = new Column<>("饲草饲料是否霉变", "scslsfmb");
        Column<Boolean> djfhbn = new Column<>("冬季防寒保暖", "djfhbn");
        Column<Boolean> mw = new Column<>("灭蚊", "mw");
        //表格数据 data 是需要填充的数据
        TableData<WelfareMessage> tableData = new TableData<>("动物福利", list, date,czlc,slkfws,ms,ystfcg,ycws,hjws,scslsfmb,djfhbn,mw);
        //设置数据
        smartTable.setTableData(tableData);
        smartTable.getConfig().setContentStyle(new FontStyle(24, Color.BLACK));
        for(int n = 0,num = list.size()-3;n <num; n += 1){
            list.remove(0);
        }
    }

    private void initView() {
        smartTable = view.findViewById(R.id.table_dwfl);
    }

    public void tableView(boolean flag, int page, List<WelfareMessage> list){
        int pre = page * 10;
        int size = list.size()%10;;
        if ((list.size() - pre) > 9){
            for(int n = 0; n < 10; n += 1){
                this.list.add(list.get(pre + n));
            }
        }else {
            for(int n = 0; n < size; n += 1){
                this.list.add(list.get(pre + n));
            }
        }
        this.flag = flag;
        this.page = page;
    }

    public void tableView(int page, List<WelfareMessage> list){
        int pre = page * 10;
        int size = list.size()%10;;
        if ((list.size() - pre) > 9){
            for(int n = 0; n < 10; n += 1){
                this.list.add(list.get(pre + n));
                initTable();
            }
        }else {
            for(int n = 0; n < size; n += 1){
                this.list.add(list.get(pre + n));
                initTable();
            }
        }

    }
}
