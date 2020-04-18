package com.example.qianmuyun;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import Fragment.MF_num_zxing;
import JDBCUtils.JDBCUtils;
import JDBCUtils.MyDruid;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import static ToolsUtils.StringUtils.format;

public class MainActivity extends AppCompatActivity implements MF_num_zxing.IOoperating{

    private final int REQUEST_CODE = 1;
    private MF_num_zxing mf_num_zxing = new MF_num_zxing();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = new Bundle();
            if (msg.what == 1){
                Intent intent = new Intent(MainActivity.this, R2_1_1.class);
                bundle.putString("编码",(String) msg.obj);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(msg.what == 2){
                Intent intent = new Intent(MainActivity.this, R2_1_2A.class);
                bundle.putString("编码",(String) msg.obj);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(msg.what == 3){
                Toast.makeText(MainActivity.this, "网络异常，数据库连接失败", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**************此函数初始化数据库连接池***************/
        Thread thread = new MyThreadC();
        thread.start();
        getSupportFragmentManager().beginTransaction().add(R.id.num_zxing_main,mf_num_zxing).commitAllowingStateLoss();
        button_tools();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //TODO:扫描结果处理
                    boolean flag = format(result);
                    if(flag){
                        //TODO：数据库查询结果
                        Thread thread = new MyThread(result);
                        thread.start();
                    }else {
                        Toast.makeText(MainActivity.this, "编码错误", Toast.LENGTH_LONG).show();
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void button_tools() {
        //动物福利
        Button button_animalWelfare = findViewById(R.id.R2_1_liukai_animalWelfare);
        button_animalWelfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_3.class);
                startActivity(intent);
            }
        });
        //系谱管理
        Button button_pedigree = findViewById(R.id.R2_1_liukai_xipumanager);
        button_pedigree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_1.class);
                startActivity(intent);
            }
        });
        //免疫档案
        Button button_Immunity = findViewById(R.id.R2_1_liukai_mianyimanager);
        button_Immunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_4.class);
                startActivity(intent);
            }
        });
        //可视化管理
        Button button_StudioManagaer = findViewById(R.id.R2_1_liukai_Studiomanager);
        button_StudioManagaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_3A.class);
                startActivity(intent);
            }
        });
        //消毒档案
        Button button_Disinfection = findViewById(R.id.R2_1_liukai_xiaodumanager);
        button_Disinfection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_5Activity.class);
                startActivity(intent);
            }
        });
        //阶段营养保健档案
        Button button_Nutrition = findViewById(R.id.R2_1_liukai_yingyangmanager);
        button_Nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_8Activity.class);
                startActivity(intent);
            }
        });
        //配种产子档案
        Button button_Breeding = findViewById(R.id.R2_1_liukai_sexmanager);
        button_Breeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_9_Activity.class);
                startActivity(intent);
            }
        });
        //育种选育档案
        Button button_Breeding_selection = findViewById(R.id.R2_1_liukai_sexselectmanager);
        button_Breeding_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_10.class);
                startActivity(intent);
            }
        });
        //疾病防治档案
        Button button_Disease_Prevention = findViewById(R.id.R2_1_liukai_jibinmanager);
        button_Disease_Prevention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_11.class);
                startActivity(intent);
            }
        });
        //驱虫档案
        Button button_qvchongn = findViewById(R.id.R2_1_liukai_qucongmanager);
        button_qvchongn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, R2_1_6.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick() {
        ZXingLibrary.initDisplayOpinion(MainActivity.this);
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void onClick(String context) {
        Thread thread = new MyThread(context);
        thread.start();
    }


    //网络操作，新建线程操作
    private class MyThread extends Thread {

        private String Number;

        private MyThread(String Number){
            this.Number = Number;
        }

        @Override
        public void run() {
            JDBCUtils jdbcUtils = new JDBCUtils();
            int result = jdbcUtils.SearchSheepNumber(Number);
            Message message = handler.obtainMessage();
            message.obj = Number;
            if(result == 1){
                message.what = 1;
            }else if(result == 0){
                message.what=2;
            }else if(result == -1){
                message.what=3;
            }
            handler.sendMessage(message);
        }
    }

    //网络操作，新建线程操作
    private class MyThreadC extends Thread {
        @Override
        public void run() {
            try {
                MyDruid.initConnect();
            } catch (Exception e) {
                e.printStackTrace();
                Message message = handler.obtainMessage();
                message.what = 3;
                handler.sendMessage(message);
            }
        }
    }
}

