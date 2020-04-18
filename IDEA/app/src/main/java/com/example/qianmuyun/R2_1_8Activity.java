package com.example.qianmuyun;

import DataUtils.Data_8;
import DataUtils.Data_81;
import DataUtils.SelectMessage;
import Fragment.Myfragment_R2_8_1;
import Fragment.Myfragment_R2_8_2;
import JDBCUtils.JDBC;
import JDBCUtils.JDBCUtils;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;

import static ToolsUtils.TimeTools.getTime;

public class R2_1_8Activity extends AppCompatActivity implements MyFragment.IOoperating{

    private MyFragment myFragment;
    private MyFragment myFragment_bj;
    private SelectMessage selectMessage;
    private SelectMessage selectMessage_2;
    private RadioGroup radioGroup;
    private Button button_enter;
    private TextView textViewTime;
    private View viewList;
    private Myfragment_R2_8_1 myfragment_r2_8_1;
    private Myfragment_R2_8_2 myfragment_r2_8_2;
    private boolean flag;
    private String stage;
    private String Medicine;
    private String method;
    private String dose_;



    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                selectMessage.setTitle("阶段：");
                myFragment = MyFragment.newInstance(selectMessage);
                getSupportFragmentManager().beginTransaction().add(R.id.R2_8_stage,myFragment).commitAllowingStateLoss();
                selectMessage_2.setTitle("保健药：");
                myFragment_bj = MyFragment.newInstance(selectMessage_2);
            }else if(msg.what==2){
                Toast.makeText(R2_1_8Activity.this,"数据加载失败，请检查网络配置",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2_1_8);

        Thread thread = new MyTreadC();
        thread.start();

        initView();
        initTime();
        initCreate();
        flag = false;

    }

    @SuppressLint("SetTextI18n")
    private void initTime() {
        String[] time;
        time = getTime().split("-");
        textViewTime.setText(time[0] + "年" + time[1] + "月" + time[2] + "日");
    }

    private void initCreate() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.R2_8_radio1:
                        radio1();
                        break;
                    case R.id.R2_8_radio2:
                        radio2();
                        break;
                }
                flag = true;
            }
        });
        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("进入zxing界面");
            }
        });
        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfragment_r2_8_1.Enter(new Myfragment_R2_8_1.IOoperating() {
                    @Override
                    public void onClick_1(Data_8 context) {

                    }
                });
                myfragment_r2_8_2.Enter(new Myfragment_R2_8_2.IOoperating() {
                    @Override
                    public void onClick_2(Data_81 context) {

                    }
                });
            }
        });
    }

    private void radio1() {
        /*从FragmentManager中移除*/
        if(flag){
            getSupportFragmentManager().beginTransaction().remove(myfragment_r2_8_2).commit();
            getSupportFragmentManager().beginTransaction().remove(myFragment_bj).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.R2_8_NutritionAndHealth,myfragment_r2_8_1).commitAllowingStateLoss();
        }else {
            getSupportFragmentManager().beginTransaction().remove(myFragment_bj).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.R2_8_NutritionAndHealth,myfragment_r2_8_1).commitAllowingStateLoss();
        }
    }

    private void radio2() {
        /*从FragmentManager中移除*/
        if(flag){
            getSupportFragmentManager().beginTransaction().remove(myfragment_r2_8_1).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.R2_8_NutritionAndHealth,myfragment_r2_8_2).commitAllowingStateLoss();
            getSupportFragmentManager().beginTransaction().add(R.id.R2_8_NutritionHealth,myFragment_bj).commitAllowingStateLoss();
        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.R2_8_NutritionAndHealth,myfragment_r2_8_2).commitAllowingStateLoss();
            getSupportFragmentManager().beginTransaction().add(R.id.R2_8_NutritionHealth,myFragment_bj).commitAllowingStateLoss();
        }
    }

    private void initView() {
        radioGroup = findViewById(R.id.R2_8_NutritionOrHealth);
        button_enter = findViewById(R.id.R2_8_enter);
        textViewTime = findViewById(R.id.R2_8_time);
        viewList = findViewById(R.id.R2_8_zxing);
        myFragment_bj = new MyFragment();
        myfragment_r2_8_1 = new Myfragment_R2_8_1();
        myfragment_r2_8_2 = new Myfragment_R2_8_2();
    }


    @Override
    public void onClick(String context, String mothod, String dose, int style) {
        switch (style){
            case 1:
                stage = context;
            case 2:
                Medicine = context;
        }
        System.out.println(context);
    }

    @Override
    public void onClick(String context, String method, String dose, String time) {

    }


    public class MyTreadC extends Thread{

        @Override
        public void run() {
            Connection connection1 = JDBC.Link();
            Connection connection2 = JDBC.Link();
            Message message=handler.obtainMessage();
            JDBCUtils jdbcUtils = new JDBCUtils();
            selectMessage = jdbcUtils.SearchStageMessage(connection1,"stage");
            selectMessage_2 = jdbcUtils.SearchTableMessage(connection2,"HealthMedicine");
            if(selectMessage != null && selectMessage_2 != null){
                message.what=1;
                handler.sendMessage(message);
            }else {
                message.what=2;
            }
        }
    }
}
