package com.example.qianmuyun;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;

import Fragment.MF_num_zxing;
import Fragment.TimeFragment;
import JDBCUtils.JDBCUtils;


import static ToolsUtils.StringUtils.format;

public class R2_1_11 extends AppCompatActivity implements MF_num_zxing.IOoperating{

    private TimeFragment timeFragment;
    private final int REQUEST_CODE = 1;
    private MF_num_zxing mf_num_zxing = new MF_num_zxing();
    private EditText editText_dong;
    private EditText editText_lan;
    private EditText editText_症状;
    private EditText editText_诊断结果;
    private EditText editText_处方;
    private EditText editText_疗程;
    private EditText editText_用药量;
    private EditText editText_治疗情况;
    private String number = "";
    private Button enter;
    private TextView textView;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = new Bundle();
            if (msg.what == 1){
                Intent intent = new Intent(R2_1_11.this, R2_1_1.class);
                bundle.putString("编码",(String) msg.obj);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(msg.what == 2){
                Intent intent = new Intent(R2_1_11.this, R2_1_2A.class);
                bundle.putString("编码",(String) msg.obj);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(msg.what == 3){
                Toast.makeText(R2_1_11.this, "网络异常，数据库连接失败", Toast.LENGTH_LONG).show();
            }else if(msg.what == 4){
                Toast.makeText(R2_1_11.this, "数据上传成功", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r2_1_11plus);
        TimeView();
        initView();
        EnterActivity();
        拍摄工作视频();
    }

    private void 拍摄工作视频() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void EnterActivity() {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<>();
                boolean flag = true;
                list.add(number);
                list.add(timeFragment.getTimeRecorde());
                list.add(editText_dong.getText().toString().trim());
                list.add(editText_lan.getText().toString().trim());
                list.add(editText_用药量.getText().toString().trim());
                list.add(editText_处方.getText().toString().trim());
                list.add(editText_治疗情况.getText().toString().trim());
                list.add(editText_疗程.getText().toString().trim());
                list.add(editText_诊断结果.getText().toString().trim());
                list.add(editText_症状.getText().toString().trim());
                for (int n = 0; n < 10; n += 1){
                    if (list.get(n).equals("")){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    Thread thread = new MyThreadW(list);
                    thread.start();
                }else {
                    Toast.makeText(R2_1_11.this, "请填写完整信息", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void initView() {
        editText_dong = findViewById(R.id.dong_R2_11);
        editText_lan = findViewById(R.id.lan_R2_11);
        editText_症状 = findViewById(R.id.症状_11);
        editText_诊断结果 = findViewById(R.id.诊断结果_11);
        editText_疗程 = findViewById(R.id.疗程_11);
        editText_治疗情况 = findViewById(R.id.治疗情况_11);
        editText_处方 = findViewById(R.id.处方_11);
        editText_用药量 = findViewById(R.id.用药量_11);
        enter = findViewById(R.id.button_11);
        textView = findViewById(R.id.相机11);
    }

    private void TimeView() {
        timeFragment = TimeFragment.newInstance("时间");
        getSupportFragmentManager().beginTransaction().add(R.id.Time,timeFragment).commitAllowingStateLoss();
        getSupportFragmentManager().beginTransaction().add(R.id.num_zxing_main,mf_num_zxing).commitAllowingStateLoss();
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
                        Thread thread = new MyThreadR(result);
                        thread.start();
                    }else {
                        Toast.makeText(R2_1_11.this, "编码错误", Toast.LENGTH_LONG).show();
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(R2_1_11.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    public void onClick() {
        ZXingLibrary.initDisplayOpinion(R2_1_11.this);
        Intent intent = new Intent(R2_1_11.this, CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void onClick(String context) {
        number = context;
    }

    //网络操作，新建线程操作 读
    private class MyThreadR extends Thread {

        private String Number;

        private MyThreadR(String Number){
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

    //网络操作，新建线程操作 读
    private class MyThreadW extends Thread {

        private List<String> list;

        private MyThreadW(List<String> list){
            this.list = list;
        }

        @Override
        public void run() {
            JDBCUtils jdbcUtils = new JDBCUtils();
            //TODO: JDBCUtils为实现此功能函数

            for (int n = 0, num = list.size();n<num;n++){
                System.out.println(list.get(n));
            }
            Message message = handler.obtainMessage();
            message.what = 4;
            handler.sendMessage(message);
        }
    }
}
