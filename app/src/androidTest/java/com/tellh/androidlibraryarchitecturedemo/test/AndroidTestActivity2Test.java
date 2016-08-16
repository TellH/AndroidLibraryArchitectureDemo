package com.tellh.androidlibraryarchitecturedemo.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tellh.androidlibraryarchitecturedemo.R;

/**
 * Created by tlh on 2016/8/16 :)
 */
public class AndroidTestActivity2Test extends ActivityInstrumentationTestCase2<AndroidTestActivity> {
    AndroidTestActivity androidTestActivity;
    private EditText etFirst;
    private EditText etSecond;
    private Button btnAdd;

    public AndroidTestActivity2Test() {
        super(AndroidTestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        androidTestActivity = getActivity();
        checkWidgets();
    }

    public void testAdd() {
        //因为测试代码不是运行在UI线程
        androidTestActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etFirst.setText("2");
                etSecond.setText("3");
            }
        });
        //等待UI线程空闲后再继续执行
        getInstrumentation().waitForIdleSync();
        //单击
        TouchUtils.clickView(AndroidTestActivity2Test.this, btnAdd);
        assertEquals(5, (int) Integer.valueOf(etFirst.getText().toString()));
    }

    private void checkWidgets() {
        etFirst = findViewById(R.id.et_first);
        etSecond = findViewById(R.id.et_second);
        btnAdd = findViewById(R.id.btn_add);
        assertNotNull(etFirst);
        assertNotNull(etSecond);
        assertNotNull(btnAdd);
    }

    private <T extends View> T findViewById(int id) {
        return (T) androidTestActivity.findViewById(id);
    }
}
