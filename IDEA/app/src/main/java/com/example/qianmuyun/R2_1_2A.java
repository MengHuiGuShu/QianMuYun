package com.example.qianmuyun;

import Fragment.Myfragment_2A;
import android.app.Activity;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;

import javax.security.auth.Destroyable;

public class R2_1_2A extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton_female;
    private RadioButton radioButton_male;
    private RadioButton radioButton_shang;
    private Myfragment_2A myfragment_2A;
    private TextInputEditText textInputEditText_num;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r2_1_2a);
//        Bundle bundle = getIntent().getExtras();        //获取上一个activity的数据
//        if (bundle != null) {
//            String s = bundle.getString("String");//数据获取
//            int num = bundle.getInt("number");
//            System.out.println(s+num);
//        }
//        Intent intent = new Intent();
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("CString","2233娘");
//        intent.putExtras(bundle1);
//        setResult(Activity.RESULT_OK,intent);
//        finish();

        initActivity();
        initFragment();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            textInputEditText_num.setText(bundle.getString("编码"));
        }
    }

    private void initFragment() {
        myfragment_2A = new Myfragment_2A();
        flag = false;
//        getSupportFragmentManager().beginTransaction().add(R.id.R2_1_2aframecol,myfragment_2A).commitAllowingStateLoss();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                    case R.id.radioButton3:
                        if(flag){
                            getSupportFragmentManager().beginTransaction().remove(myfragment_2A).commit();
                            flag = false;
                        }
                        break;
                    case R.id.radioButton2:
                        if(!flag) {
                            getSupportFragmentManager().beginTransaction().add(R.id.R2_1_2aframecol,myfragment_2A).commitAllowingStateLoss();
                            flag = true;
                        }
                        break;
                }
            }
        });
    }

    private void initActivity() {
        radioGroup = findViewById(R.id.radioGroup3);
        radioButton_male = findViewById(R.id.radioButton);
        radioButton_female = findViewById(R.id.radioButton2);
        radioButton_shang = findViewById(R.id.radioButton3);
        textInputEditText_num = findViewById(R.id.R2_1_2a_num);
    }
}
