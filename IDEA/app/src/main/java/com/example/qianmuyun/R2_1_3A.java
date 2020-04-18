package com.example.qianmuyun;

import ExcelTools.Excel_Tools;
import JDBCUtils.JDBCUtils;
import ToolsUtils.WelfareMessage;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.List;

public class R2_1_3A extends AppCompatActivity {
    private static String excelPath;
    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private int REQUEST_PERMISSION_CODE = 1000;
    private AlertDialog alertDialog;
    private AlertDialog mDialog;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                List<WelfareMessage> WelfareMessageList = (List<WelfareMessage>) msg.obj;
                Excel_Tools.writeObjListToExcel(WelfareMessageList, excelPath, R2_1_3A.this,1);
            }else if(msg.what==2){
                Toast.makeText(R2_1_3A.this,"网络操作错误，请检查网络",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void requestPermission() {
        if (Build.VERSION.SDK_INT > 23) {
            if (ContextCompat.checkSelfPermission(R2_1_3A.this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
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
                AlertDialog.Builder builder = new AlertDialog.Builder(R2_1_3A.this);
                builder.setTitle("permission")
                        .setMessage("点击允许才可以使用我们的app哦")
                        .setPositiveButton("去允许", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (alertDialog != null && alertDialog.isShowing()) {
                                    alertDialog.dismiss();
                                }
                                ActivityCompat.requestPermissions(R2_1_3A.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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

    private void showDialogTipUserRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2_1_3a);

        requestPermission();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        Button button = findViewById(R.id.xls);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(R2_1_3A.this,"请检查SD卡状态",Toast.LENGTH_SHORT).show();
                    return;
                }
                File filePath = Environment.getExternalStorageDirectory();
                File file = new File(String.valueOf(filePath));
                String excelFileName = "/WelfareMessage.xls";//excelmanagar
                excelPath = file + excelFileName;

                String[] title = {"检查日期", "操作流程是否规范", "饲料库房是否清洁","灭鼠是否到位","羊舍通风采光是否合理","羊床卫生是否清洁",
                        "环境卫生是否清洁", "饲草饲料是否霉变","冬季防寒保暖是否到位","灭蚊是否到位"};
                String sheetName = "WelfareMessage";
                Excel_Tools.initExcel(excelPath, sheetName, title);
                Thread thread = new MyThread();
                thread.start();
            }
        });
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            JDBCUtils jdbcUtils = new JDBCUtils();
            List<WelfareMessage> list = jdbcUtils.SearchWelfareMessage();
            Message message=handler.obtainMessage();
            if(list == null){
                message.what=2;
            }else {
                message.what=1;
                message.obj=list;
                handler.sendMessage(message);
            }

        }
    }
}
