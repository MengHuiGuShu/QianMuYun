package com.example.qianmuyun;

import JDBCUtils.JDBCUtils;
import ToolsUtils.*;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static ToolsUtils.StringUtils.format;
import static ToolsUtils.TimeTools.getTime;

public class R2_1_1 extends AppCompatActivity {

    private EditText SearchEditText;
    private EditText et_address;
    private EditText et_birthAddress;
    private EditText et_birthday;
    private EditText et_birthWeight;
    private EditText et_variety;
    private EditText et_m;
    private EditText et_f;
    private EditText et_mf;
    private EditText et_ff;
    private EditText et_mm;
    private EditText et_fm;
    private RadioGroup radioGroup;
    private MultiLineRadioGroup multiLineRadioGroup;
    private TextInputEditText textInputEditText;
    static PedigreeMessage pedigreeMessage = new PedigreeMessage();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int arg1=msg.arg1;
            if (msg.what==1){                                                                                 //增
                PedigreeMessage pedigreeMessage = (PedigreeMessage) msg.obj;
                initViewMessage(pedigreeMessage);
            }else if(msg.what==2){
                Toast.makeText(R2_1_1.this,"数据录入成功",Toast.LENGTH_SHORT).show();
            }else if(msg.what==3){
                pedigreeMessage.setnull();
                initViewMessage(pedigreeMessage);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r2_1_1plus);
        initID();
        initView();
        initRadioGroup();
        initSearchEditText();
        Button enter = findViewById(R.id.button13);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEnterMessage();
            }
        });
        String fileState = Environment.getExternalStorageState();
        if(fileState.equals(Environment.MEDIA_MOUNTED)){
            String excelFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            System.out.println(excelFilePath);
            excelFilePath = excelFilePath + "/" + getTime() +"Pedigree.xls";
            System.out.println(excelFilePath);
        }else {
            Toast.makeText(R2_1_1.this,"SD卡没有正常挂载", Toast.LENGTH_SHORT).show();
        }

        //GetLocation();
    }

    private void initID() {
        radioGroup = findViewById(R.id.radioGroup4);
        multiLineRadioGroup = findViewById(R.id.multiLineRadioGroup4);
        et_address = findViewById(R.id.R2__1_1_liukai_text5);
        et_birthAddress = findViewById(R.id.R2__1_1_liukai_text6);
        et_birthday = findViewById(R.id.R2__1_1_liukai_text7);
        et_birthWeight = findViewById(R.id.R2__1_1_liukai_text8);
        et_variety = findViewById(R.id.R2__1_1_liukai_text9);
        et_m = findViewById(R.id.R2__1_1_liukai_text11);
        et_f = findViewById(R.id.R2__1_1_liukai_text12);
        et_mf = findViewById(R.id.R2__1_1_liukai_text13);
        et_ff = findViewById(R.id.R2__1_1_liukai_text14);
        et_mm = findViewById(R.id.R2__1_1_liukai_text15);
        et_fm = findViewById(R.id.R2__1_1_liukai_text16);
        SearchEditText = findViewById(R.id.R2_1_1_liukai_editerbiaohao);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            SearchEditText.setText(bundle.getString("编码"));
            Thread thread = new MyThreadRead(bundle.getString("编码"),pedigreeMessage);
            thread.start();
        }
    }

    private void GetLocation(){
        LocationUtils.register(this, 0, 0, new LocationUtils.OnLocationChangeListener() {
            @Override
            public void getLastKnownLocation(Location location) {
                System.out.println("onLocationChanged: " + location.getLatitude());
            }

            @Override
            public void onLocationChanged(Location location) {
                System.out.println("定位方式：" + location.getProvider());
                System.out.println("纬度：" + location.getLatitude());
                System.out.println("经度：" + location.getLongitude());
                System.out.println("海拔：" + location.getAltitude());
                System.out.println("时间：" + location.getTime());
                System.out.println("国家：" + LocationUtils.getCountryName(R2_1_1.this, location.getLatitude(), location.getLongitude()));
                System.out.println("获取地理位置：" + LocationUtils.getAddress(R2_1_1.this, location.getLatitude(), location.getLongitude()));
                System.out.println("所在地：" + LocationUtils.getLocality(R2_1_1.this, location.getLatitude(), location.getLongitude()));
                System.out.println("所在街道：" + LocationUtils.getStreet(R2_1_1.this, location.getLatitude(), location.getLongitude()));
                EditText et_Address = findViewById(R.id.R2__1_1_liukai_text5);
                EditText et_BirthAddress = findViewById(R.id.R2__1_1_liukai_text6);
                et_Address.setText(LocationUtils.getStreet(R2_1_1.this, location.getLatitude(), location.getLongitude()));
                et_BirthAddress.setText(LocationUtils.getStreet(R2_1_1.this, location.getLatitude(), location.getLongitude()));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtils.unregister();
    }

    private void initRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton11:
                        pedigreeMessage.setSexMale();
                    case R.id.radioButton12:
                        pedigreeMessage.setSexFemale();
                }
            }
        });

        multiLineRadioGroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MultiLineRadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_black:
                        pedigreeMessage.setCoatColor("1");                  //1:黑色
                    case R.id.radioButton_white:
                        pedigreeMessage.setCoatColor("2");                  //2:白色
                    case R.id.radioButton_yellow:
                        pedigreeMessage.setCoatColor("3");                  //3:黄色
                    case R.id.radioButton_flower:
                        pedigreeMessage.setCoatColor("4");                  //4:花色
                    case R.id.radioButton_jute:
                        pedigreeMessage.setCoatColor("5");                  //5:麻黄色
                    case R.id.radioButton_Blackhemp:
                        pedigreeMessage.setCoatColor("6");                  //6:麻黑色
                    case R.id.radioButton_grasswhite:
                        pedigreeMessage.setCoatColor("7");                  //7:草白色
                }
            }
        });
    }

    public void initEnterMessage(){
        int num = 0;
        if(SearchEditText.getText().toString().trim().isEmpty()){
            Toast.makeText(R2_1_1.this,"请输入要录入的羊只编号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(pedigreeMessage.getSex().isEmpty())
        {
            num += 1;
        }
        if(pedigreeMessage.getCoatColor().isEmpty()){
            num += 1;
        }
        if(et_birthday.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_birthAddress.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_f.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_fm.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_ff.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_mf.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_m.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_mm.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_variety.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_birthWeight.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(et_address.getText().toString().trim().isEmpty()){
            num += 1;
        }
        if(num > 0){
            Toast.makeText(R2_1_1.this,"还有"+num+"项内容没有填写",Toast.LENGTH_SHORT).show();
        }
        pedigreeMessage.setBirthday(et_birthday.getText().toString().trim());
        pedigreeMessage.setBirthAddress(et_birthAddress.getText().toString().trim());
        pedigreeMessage.setFather(et_f.getText().toString().trim());
        pedigreeMessage.setFathermother(et_fm.getText().toString().trim());
        pedigreeMessage.setFatherfather(et_ff.getText().toString().trim());
        pedigreeMessage.setMotherfather(et_mf.getText().toString().trim());
        pedigreeMessage.setMother(et_m.getText().toString().trim());
        pedigreeMessage.setMothermother(et_mm.getText().toString().trim());
        pedigreeMessage.setVariety(et_variety.getText().toString().trim());
        pedigreeMessage.setBirthWeight(et_birthWeight.getText().toString().trim());
        pedigreeMessage.setAddress(et_address.getText().toString().trim());
        Thread thread = new MyThreadStore(pedigreeMessage);
        thread.start();
    }


    public void initSearchEditText(){
        SearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    hideKeyboard(SearchEditText);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    //此处为连接数据库，进行数据编号查询
                    String Number = SearchEditText.getText().toString().trim();
                    boolean result = format(Number);
                    if(result){         //编码格式正确，进行查询录入
                        Thread thread = new MyThreadRead(Number,pedigreeMessage);
                        thread.start();


                    } else {            //输入非法格式，则保存，并且不进行录入与查询
                        SearchEditText.setText("");
                        SearchEditText.setError("coding error");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 隐藏软键盘
     *  context :上下文
     * @param view    :一般为EditText
     */
    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert manager != null;
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initViewMessage(PedigreeMessage pedigreeMessage){
        et_address.setText(pedigreeMessage.getAddress());
        et_birthAddress.setText(pedigreeMessage.getBirthAddress());
        et_birthday.setText(pedigreeMessage.getBirthday());
        et_birthWeight.setText(pedigreeMessage.getBirthWeight());
        et_f.setText(pedigreeMessage.getFather());
        et_ff.setText(pedigreeMessage.getFatherfather());
        et_fm.setText(pedigreeMessage.getFathermother());
        et_m.setText(pedigreeMessage.getMother());
        et_mf.setText(pedigreeMessage.getMotherfather());
        et_mm.setText(pedigreeMessage.getMothermother());
        et_variety.setText(pedigreeMessage.getVariety());
        switch (pedigreeMessage.getSex()) {
            case "1":
                radioGroup.check(R.id.R2_1_1_liukai_male);
                break;
            case "0":
                radioGroup.check(R.id.R2_1_1_liukai_female);
                break;
            case "":
                radioGroup.clearCheck();
                break;
        }
        switch (pedigreeMessage.getCoatColor()) {
            case "":
                multiLineRadioGroup.clearCheck();
                break;
            case "1":
                multiLineRadioGroup.check(R.id.radioButton_black);
                break;
            case "2":
                multiLineRadioGroup.check(R.id.radioButton_white);
                break;
            case "3":
                multiLineRadioGroup.check(R.id.radioButton_yellow);
                break;
            case "4":
                multiLineRadioGroup.check(R.id.radioButton_flower);
                break;
            case "5":
                multiLineRadioGroup.check(R.id.radioButton_jute);
                break;
            case "6":
                multiLineRadioGroup.check(R.id.radioButton_Blackhemp);
                break;
            case "7":
                multiLineRadioGroup.check(R.id.radioButton_grasswhite);
                break;
        }
    }

    private void initView() {

    }

    public class MyThreadRead extends Thread {

        private String Number;
        private PedigreeMessage pedigreeMessage;      //回调函数

        private MyThreadRead(String Number, PedigreeMessage pedigreeMessage){
            this.Number = Number;
            this.pedigreeMessage = pedigreeMessage;
        }

        @Override
        public void run() {
            JDBCUtils jdbcUtils = new JDBCUtils();
            boolean result = jdbcUtils.SearchPedigreeMessage(Number,pedigreeMessage);
            if(result){
                Message message=handler.obtainMessage();
                message.what=1;
                message.obj=pedigreeMessage;
                handler.sendMessage(message);
            }else {
                Message message=handler.obtainMessage();
                message.what=3;
                handler.sendMessage(message);
            }
        }
    }

    public class MyThreadStore extends Thread {

        private PedigreeMessage pedigreeMessage;      //回调函数

        private MyThreadStore(PedigreeMessage pedigreeMessage){
            this.pedigreeMessage = pedigreeMessage;
        }

        @Override
        public void run() {
            JDBCUtils jdbcUtils = new JDBCUtils();
            boolean result = jdbcUtils.storePedigreeMessage(pedigreeMessage);
            if(result){
                Message message=handler.obtainMessage();
                message.what=2;
                handler.sendMessage(message);
            }
        }
    }
}
