package com.tellh.androidlibraryarchitecturedemo.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.Toast;

import com.tellh.androidlibraryarchitecturedemo.R;


public class DataBindPrimaryPracticeActivity extends AppCompatActivity {
    Person male;
    Person female;
    private EditText edtNewName;
    private String newName;
    private com.tellh.androidlibraryarchitecturedemo.databinding.ActivityDataBindPrimaryPracticeBinding dataBindPrimaryPracticeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_bind_primary_practice);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind_primary_practice);
        dataBindPrimaryPracticeBinding = (com.tellh.androidlibraryarchitecturedemo.databinding.ActivityDataBindPrimaryPracticeBinding) binding;
        male = new Person(true, 20, "TellH");
        female = new Person(false, 18, "Ems");
        dataBindPrimaryPracticeBinding.setTitle("Wanted?");
        dataBindPrimaryPracticeBinding.setMale(male);
        dataBindPrimaryPracticeBinding.setFemale(female);
        Handler handler = new Handler(this);
        dataBindPrimaryPracticeBinding.setHandler(handler);
        //只要给 View 定义一个 ID，Data Binding 就会为我们生成一个对应的 final 变量
        dataBindPrimaryPracticeBinding.tvWithId.setText("");
        dataBindPrimaryPracticeBinding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                com.tellh.androidlibraryarchitecturedemo.databinding.ViewStubDatabindingBinding bind = DataBindingUtil.bind(inflated);
                bind.setPerson(female);
            }
        });
        //or
//        binding.setVariable(BR.male,male);
//        binding.setVariable(BR.female,female);
//        binding.setVariable(BR.title,"");
    }

    public void onClickBtnLoadViewStub(View view) {
        if (!dataBindPrimaryPracticeBinding.viewStub.isInflated()) {
            dataBindPrimaryPracticeBinding.viewStub.getViewStub().inflate();
        }
    }

    public class Handler {
        Context context;

        public Handler(Context context) {
            this.context = context;
        }

        //方法签名与对应的Listener相同
        public void onAgeTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(s) && TextUtils.isDigitsOnly(s))
                female.setAge(Integer.parseInt(s.toString()));
        }

        //用Lambda表达式可以用自定义的方法签名的监听器
        public void onClickTestBtn(Person person) {
            Toast.makeText(context, "you got me!", Toast.LENGTH_SHORT).show();
        }

        public void onNameTextChanged(CharSequence s, Person person) {
            person.setName(s.toString());
        }
    }
}

