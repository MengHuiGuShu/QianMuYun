package com.example.qianmuyun;

import JDBCUtils.JDBCUtils;
import ToolsUtils.WelfareMessage;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static ToolsUtils.TimeTools.getTime;

public class R2_1_3 extends AppCompatActivity {

    static WelfareMessage welfareMessage = new WelfareMessage("",false,false,false,false,
            false,false,false,false,false);
    static boolean flag = false;            //数据库储存 线程操作完成信号，待用中
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2_1_3);

        Button List_button = findViewById(R.id.dwfl_Listbutton3);
        List_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RadioGroup radioGroup1 = findViewById(R.id.操作流程);
        RadioGroup radioGroup2 = findViewById(R.id.冬季防寒保暖是否到位);
        RadioGroup radioGroup3 = findViewById(R.id.灭蚊是否到位是否到位);
        RadioGroup radioGroup4 = findViewById(R.id.环境卫生是否清洁);
        RadioGroup radioGroup5 = findViewById(R.id.灭鼠是否到位);
        RadioGroup radioGroup6 = findViewById(R.id.羊床卫生是否清洁);
        RadioGroup radioGroup7 = findViewById(R.id.饲料库房清洁);
        RadioGroup radioGroup8 = findViewById(R.id.饲草饲料是否霉变);
        RadioGroup radioGroup9 = findViewById(R.id.羊舍通风采光是否合理);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setCzlc();
                switch (checkedId){
                    case R.id.操作流程Yes:
                        welfareMessage.flagczlc();
                        break;
                    case R.id.操作流程No:
                        welfareMessage.unflagczlc();
                        break;
                }
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setDjfhbn();
                switch (checkedId){
                    case R.id.冬季防寒保暖是否到位Yes:
                        welfareMessage.flagdjfhbn();
                        break;
                    case R.id.冬季防寒保暖是否到位No:
                        welfareMessage.unflagdjfhbn();
                        break;
                }
            }
        });
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setMw();
                switch (checkedId){
                    case R.id.灭蚊是否到位是否到位Yes:
                        welfareMessage.flagmw();
                        break;
                    case R.id.灭蚊是否到位是否到位No:
                        welfareMessage.unflagmw();
                        break;
                }
            }
        });
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setHjws();
                switch (checkedId){
                    case R.id.环境卫生是否清洁Yes:
                        welfareMessage.flaghjws();
                        break;
                    case R.id.环境卫生是否清洁No:
                        welfareMessage.unflaghjws();
                        break;
                }
            }
        });
        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setMs();
                switch (checkedId){
                    case R.id.灭鼠是否到位Yes:
                        welfareMessage.flagms();
                        break;
                    case R.id.灭鼠是否到位No:
                        welfareMessage.unflagms();
                        break;
                }
            }
        });
        radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setYcws();
                switch (checkedId){
                    case R.id.羊床卫生是否清洁Yes:
                        welfareMessage.flagycws();
                        break;
                    case R.id.羊床卫生是否清洁No:
                        welfareMessage.unflagycws();
                        break;
                }
            }
        });
        radioGroup7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setSlkfws();
                switch (checkedId){
                    case R.id.饲料库房清洁Yes:
                        welfareMessage.flagslkfws();
                        break;
                    case R.id.饲料库房清洁No:
                        welfareMessage.unflagslkfws();
                        break;
                }
            }
        });
        radioGroup8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setScslsfmb();
                switch (checkedId){
                    case R.id.饲草饲料是否霉变Yes:
                        welfareMessage.flagscslsfmb();
                        break;
                    case R.id.饲草饲料是否霉变No:
                        welfareMessage.unflagscslsfmb();
                        break;
                }
            }
        });
        radioGroup9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                welfareMessage.setYstfcg();
                switch (checkedId){
                    case R.id.羊舍通风采光是否合理Yes:
                        welfareMessage.flagystftg();
                        break;
                    case R.id.羊舍通风采光是否合理No:
                        welfareMessage.unflagystftg();
                        break;
                }
            }
        });
        List_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(R2_1_3.this,ListActivity.class);
                startActivity(intent);
            }
        });
        Button button = findViewById(R.id.R2_1_3_enter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = welfareMessage.getAllFlag();
                if (flag){
                    Thread thread = new MyThread(welfareMessage,getTime());
                    thread.start();
                    Toast.makeText(R2_1_3.this,"动物福利信息记录成功",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(R2_1_3.this,"有未完成选项",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static class MyThread extends Thread {

        private WelfareMessage welfareMessage;
        private String time;

        private MyThread(WelfareMessage welfareMessage, String time){
            this.welfareMessage = welfareMessage;
            this.time = time;
        }

        @Override
        public void run() {
            JDBCUtils jdbcUtils = new JDBCUtils();
            flag = jdbcUtils.storeAnimalWefareMessage(welfareMessage,time);
        }
    }
}
