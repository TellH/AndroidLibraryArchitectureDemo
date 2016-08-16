package com.tellh.androidlibraryarchitecturedemo.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tellh.androidlibraryarchitecturedemo.R;

public class AndroidTestActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFirst;
    private EditText etSecond;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_test);
        initView();

    }

    private void initView() {
        etFirst   = (EditText) findViewById(R.id.et_first);
        etSecond  = (EditText) findViewById(R.id.et_second);
        btnAdd    = (Button)   findViewById(R.id.btn_add);
        assert btnAdd != null;
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                int param1 = Integer.valueOf(etFirst.getText().toString().trim());
                int param2 = Integer.valueOf(etSecond.getText().toString().trim());
                int result = param1 + param2;
                etFirst.setText(String.valueOf(result));
                break;
        }
    }

}
