package com.example.qianmuyun;

import DataUtils.SelectMessage;
import JDBCUtils.JDBC;
import JDBCUtils.JDBCUtils;
import ToolsUtils.DisinfectionRecords;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Connection;

import static ToolsUtils.TimeTools.getTime;

public class R2_1_6 extends AppCompatActivity implements MyFragment.IOoperating{

    private SelectMessage selectMessage;
    private MyFragment myFragment;
    private Button button_enter;
    private EditText editTextMethod;
    private EditText editTextDose;
    private String disinfectant;
    private TextView textViewTime;
    DisinfectionRecords disinfectionRecords = new DisinfectionRecords();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if(msg.arg1 == 1){
                    selectMessage = (SelectMessage) msg.obj;
                    selectMessage.setTitle("  消毒药");
                    myFragment = MyFragment.newInstance(selectMessage);
                    getSupportFragmentManager().beginTransaction().add(R.id.R2_6_fragment,myFragment).commitAllowingStateLoss();
                }
            }else if(msg.what==2){
                Toast.makeText(R2_1_6.this,"网络操作异常",Toast.LENGTH_SHORT).show();
            }else if(msg.what==5){
                Toast.makeText(R2_1_6.this,"网络操作异常，数据未成功录入",Toast.LENGTH_SHORT).show();
            }
            else if(msg.what==4){
                Toast.makeText(R2_1_6.this,"数据保存成功",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2_1_6);

        Thread threadc = new MyTreadC(true);
        threadc.start();

        button_enter = findViewById(R.id.button_R2_6);
        editTextMethod = findViewById(R.id.method_61);
        editTextDose = findViewById(R.id.dose_61);
        textViewTime = findViewById(R.id.textView_61);
        String[] time = getTime().split("-");
        final String timeValie = time[0] + ":" + time[1] + ":" + time[2];
        textViewTime.setText("消毒时间:" + time[0] + "年" + time[1] + "月" + time[2] + "日");
        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disinfectionRecords.setTime(timeValie);
                disinfectionRecords.setName(disinfectant);
                disinfectionRecords.setMethod(editTextMethod.getText().toString().trim());
                disinfectionRecords.setDose(editTextDose.getText().toString().trim());
                //TODO:还有信息未录入，可在此增加录入过程

                //TODO:开启线程网络连接，数据传输操作
                if(disinfectionRecords.CheckMessage() == 0){
                    Thread thread = new R2_1_6.MyTread(disinfectionRecords);
                    thread.start();
                }else {
                    Toast.makeText(R2_1_6.this,"还有"+disinfectionRecords.CheckMessage()+"项信息未完成",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(String context,String method,String dose,int style) {
        disinfectant = context;
        editTextDose.setText(method);
        editTextMethod.setText(dose);
    }

    @Override
    public void onClick(String context, String method, String dose, String time) {

    }

    public class MyTreadC extends Thread{
        private boolean flag;
        private MyTreadC(boolean flag){
            this.flag = flag;
        }

        @Override
        public void run() {
            Connection connection = JDBC.Link();
            Message message=handler.obtainMessage();
            JDBCUtils jdbcUtils = new JDBCUtils();
            selectMessage = jdbcUtils.SearchTableMessage(connection,"消毒药");
            if(selectMessage != null){
                message.what=1;
                message.arg1=1;
                message.obj = selectMessage;
                handler.sendMessage(message);
            }else {
                message.what=2;
            }
        }
    }

    public class MyTread extends Thread{
        private DisinfectionRecords disinfectionRecords;

        public MyTread(DisinfectionRecords disinfectionRecords) {
            this.disinfectionRecords = disinfectionRecords;
        }

        @Override
        public void run() {
            super.run();
            JDBCUtils jdbcUtils = new JDBCUtils();
            int result = jdbcUtils.StoreDisinfectionRecords(disinfectionRecords);
            Message message=handler.obtainMessage();
            if(result == 0){
                message.what=5;
            }else {
                message.what=4;
            }
            handler.sendMessage(message);
        }
    }
}
