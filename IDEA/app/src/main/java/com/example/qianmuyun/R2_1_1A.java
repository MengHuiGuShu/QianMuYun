package com.example.qianmuyun;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;

public class R2_1_1A extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2_1_1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        initView();
        if (bundle != null) {

        }
    }

    private void initView() {

    }
}
