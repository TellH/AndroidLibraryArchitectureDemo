package com.tellh.androidlibraryarchitecturedemo.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tellh.androidlibraryarchitecturedemo.R;

/**
 * Created by tlh on 2016/8/16 :)
 */
public class AndroidTestActivityTest extends ActivityUnitTestCase<AndroidTestActivity> {

    //必须继承Activity
    AndroidTestActivity androidTestActivity;
    private EditText etFirst;
    private EditText etSecond;
    private Button btnAdd;

    public AndroidTestActivityTest() {
        super(AndroidTestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        launchActivity();
        androidTestActivity = getActivity();
        checkWidgets();
    }

    private void checkWidgets() {
        etFirst = findViewById(R.id.et_first);
        etSecond = findViewById(R.id.et_second);
        btnAdd = findViewById(R.id.btn_add);
        assertNotNull(etFirst);
        assertNotNull(etSecond);
        assertNotNull(btnAdd);
    }

    public void testAdd(){
        etFirst.setText("2");
        etSecond.setText("3");
        btnAdd.performClick();
        assertEquals(5,(int)Integer.valueOf(etFirst.getText().toString()));
    }

    private void launchActivity() {
        Intent intent = new Intent(getInstrumentation().getTargetContext(), AndroidTestActivity.class);
        startActivity(intent, null, null);
    }

    private <T extends View> T findViewById(int id) {
        return (T) androidTestActivity.findViewById(id);
    }
}