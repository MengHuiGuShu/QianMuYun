package com.example.qianmuyun;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.column.ColumnInfo;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.bin.david.form.data.format.selected.BaseSelectFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.PageTableData;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ExcelTools.Excel_Tools;
import Fragment.ChangeTableFragment;
import JDBCUtils.JDBCUtils_List;
import ToolsUtils.WelfareMessage;

public class ListActivity extends AppCompatActivity implements ChangeTableFragment.IOoperating{
    private SmartTable smartTable;
    private static String excelPath;
    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private int REQUEST_PERMISSION_CODE = 1000;
    private AlertDialog alertDialog;
    private AlertDialog mDialog;
    private int listStyle;
    private Button pageup;
    private Button pagedown;
    private Button export;
    private int page = 0;
    private List<WelfareMessage> list = new ArrayList<>();
    private List<WelfareMessage> table;
    private PageTableData<WelfareMessage> pageTableData;
    private ChangeTableFragment changeTableFragment = new ChangeTableFragment();
    final private int TextSize = 12;

    private Column<String> date = new Column<String>("时间", "date");
    private Column<Boolean> czlc = new Column<>("操作流程是否规范", "czlc");
    private Column<Boolean> slkfws = new Column<>("饲料库房卫生", "slkfws");
    private Column<Boolean> ms = new Column<>("灭鼠", "ms");
    private Column<Boolean> ystfcg = new Column<>("羊舍通风采光", "ystfcg");
    private Column<Boolean> ycws = new Column<>("羊床卫生", "ycws");
    private Column<Boolean> hjws = new Column<>("环境卫生", "hjws");
    private Column<Boolean> scslsfmb = new Column<>("饲草饲料是否霉变", "scslsfmb");
    private Column<Boolean> djfhbn = new Column<>("冬季防寒保暖", "djfhbn");
    private Column<Boolean> mw = new Column<>("灭蚊", "mw");


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 50){
                table = (List<WelfareMessage>) msg.obj;
                for (int n = 0;table.size()>n;n+=1){
                    list.add(table.get(n));
                }
                date = new Column<String>("时间", "date");
                czlc = new Column<>("操作流程是否规范", "czlc");
                slkfws = new Column<>("饲料库房卫生", "slkfws");
                ms = new Column<>("灭鼠", "ms");
                ystfcg = new Column<>("羊舍通风采光", "ystfcg");
                ycws = new Column<>("羊床卫生", "ycws");
                hjws = new Column<>("环境卫生", "hjws");
                scslsfmb = new Column<>("饲草饲料是否霉变", "scslsfmb");
                djfhbn = new Column<>("冬季防寒保暖", "djfhbn");
                mw = new Column<>("灭蚊", "mw");
                //表格数据 data 是需要填充的数据
                pageTableData = new PageTableData<WelfareMessage>("动物福利", table, date,czlc,slkfws,ms,ystfcg,ycws,hjws,scslsfmb,djfhbn,mw);
                pageTableData.setPageSize(10);
                pageTableData.setCurrentPage(page);
                smartTable.setTableData(pageTableData);
                /*****************************************美化@刘凯********************************************************/
                smartTable.getConfig().setContentStyle(new FontStyle(48, Color.DKGRAY));
                smartTable.getConfig().setMinTableWidth(500);
                smartTable.setZoom(true);
                date.setFast(true);//可以设置放大最大和最小值

                ICellBackgroundFormat<CellInfo> backgroundFormat = new BaseCellBackgroundFormat<CellInfo>() {
                    @Override
                    public int getBackGroundColor(CellInfo cellInfo) {
                        if(cellInfo.row %2 == 0) {
                            return ContextCompat.getColor(ListActivity.this, R.color.table);
                        }else{
                            return TableConfig.INVALID_COLOR; //返回无效颜色，不会绘制
                        }
                    }
                };
                smartTable.getConfig().setContentCellBackgroundFormat(backgroundFormat);
                smartTable.setSelectFormat(new BaseSelectFormat());
                /****************************************************************************************************/
                //元素点击事件
                pageTableData.setOnItemClickListener(new TableData.OnItemClickListener() {
                    @Override
                    public void onClick(Column column, String value, Object o, int col, int row) {
                        System.out.println(value);
                        System.out.println(col);
                        System.out.println(row);
                        changeTable(column.getColumnName(),col,row);

                    }
                });
            }else if(msg.what == 999){
                Toast.makeText(ListActivity.this,"网络操作错误，请检查网络",Toast.LENGTH_SHORT).show();
            }else if (msg.what == 100){
                List<WelfareMessage> WelfareMessageList = (List<WelfareMessage>) msg.obj;
                Excel_Tools.writeObjListToExcel(WelfareMessageList, excelPath, ListActivity.this,1);
            }
        }
    };

    private void changeTable(String s,int col, int row) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentTableChange,changeTableFragment).commitAllowingStateLoss();
        changeTableFragment.initInputType(s,col,row);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libiaoactivity);
        bundleDate();
        initView();
        createList();
        requestPermission();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        pageup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 0){
                    page -= 1;
                    pageTableData.setCurrentPage(page);
                    smartTable.setTableData(pageTableData);
                }
            }
        });
        pagedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (page < pageTableData.getTotalPage()){
                    page += 1;
                    pageTableData.setCurrentPage(page);
                    smartTable.setTableData(pageTableData);
                }
            }
        });
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(ListActivity.this,"请检查SD卡状态",Toast.LENGTH_SHORT).show();
                    return;
                }
                File filePath = Environment.getExternalStorageDirectory();
                File file = new File(String.valueOf(filePath));
                String excelFileName = "/WelfareMessage.xls";
                excelPath = file + excelFileName;

                String[] title = {"检查日期", "操作流程是否规范", "饲料库房是否清洁","灭鼠是否到位","羊舍通风采光是否合理","羊床卫生是否清洁",
                        "环境卫生是否清洁", "饲草饲料是否霉变","冬季防寒保暖是否到位","灭蚊是否到位"};
                String sheetName = "WelfareMessage";
                Excel_Tools.initExcel(excelPath, sheetName, title);
                Thread thread = new MyThread(100);
                thread.start();
            }
        });
        //列点击事件
        smartTable.setOnColumnClickListener(new OnColumnClickListener() {
            @Override
            public void onClick(ColumnInfo columnInfo) {
                System.out.println("ccccccccccccccccccccccccccccccccccc");
                try {
                    System.out.println(columnInfo.value);
                    System.out.println(columnInfo.column);

                }catch (Exception e){
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");
                }
            }
        });
    }

    private void createList() {
        Thread thread = new MyThread(50);
        thread.start();
    }

    //获取上一个activity传来的数据，用于判断是哪个界面跳转过来，要生成什么样式的列表
    private void bundleDate() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            listStyle = bundle.getInt("ListStyle");
        }
    }

    private void initView() {
        smartTable = findViewById(R.id.table);
        pageup = findViewById(R.id.pageup);
        pagedown = findViewById(R.id.pagedown);
        export = findViewById(R.id.export);
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT > 23) {
            if (ContextCompat.checkSelfPermission(ListActivity.this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
                //授予权限
                Log.i("requestPermission:", "用户之前已经授予了权限！");
            } else {
                //未获得权限
                Log.i("requestPermission:", "未获得权限，现在申请！");
                requestPermissions(permissions, REQUEST_PERMISSION_CODE);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("onPermissionsResult:", "权限" + permissions[0] + "申请成功");
                // permission was granted, yay! Do the
                // contacts-related task you need to do.

            } else {
                Log.i("onPermissionsResult:", "用户拒绝了权限申请");
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("permission")
                        .setMessage("点击允许才可以使用我们的app哦")
                        .setPositiveButton("去允许", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (alertDialog != null && alertDialog.isShowing()) {
                                    alertDialog.dismiss();
                                }
                                ActivityCompat.requestPermissions(ListActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                        });
                alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }
    }

    @Override
    public void onClick(String context,int col,int row) {
        getSupportFragmentManager().beginTransaction().remove(changeTableFragment).commit();
        table.get(col).setDate(context);
        smartTable.setTableData(pageTableData);
    }


    private class MyThread extends Thread {

        private int flag;

        private MyThread(int flag){
            this.flag = flag;
        }

        @Override
        public void run() {
            JDBCUtils_List jdbcUtils_list = new JDBCUtils_List();
            List<WelfareMessage> list = jdbcUtils_list.readSQL();
            Message message = handler.obtainMessage();
            if (list == null) {
                message.what = 999;
            } else {
                message.what = flag;
                message.obj = list;
                handler.sendMessage(message);
            }
        }
    }
}
