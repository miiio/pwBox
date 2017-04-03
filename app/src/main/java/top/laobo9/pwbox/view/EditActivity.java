package top.laobo9.pwbox.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import bean.Data;
import util.*;
import top.laobo9.pwbox.R;

/**
 * Created by wax on 2017/4/1.
 */

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private Data mData=null;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private String type;
    private int mType;

    private EditText title;
    private EditText username ;
    private EditText password;
    private EditText mark ;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mType = bundle.getInt("type");
        type = bundle.getString("addOredit");
        getView();
        initView();
        if(type.equals("edit")){
            mData = (Data) bundle.getSerializable("data");
            title.setText(mData.getmTitle());
            username.setText(mData.getmUsername());
            password.setText(mData.getmPassword());
            mark.setText(mData.getmMark());
        }
    }
    private void getView(){
        mToolbar = (Toolbar)findViewById(R.id.add_toolbar);
        title = (EditText)findViewById(R.id.add_title);
        username = (EditText)findViewById(R.id.add_username);
        password = (EditText)findViewById(R.id.add_pw);
        mark = (EditText)findViewById(R.id.add_mark);
        btn = (Button)findViewById(R.id.add_btn);
    }
    private void initView(){
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        if(type.equals("add"))
            mActionBar.setTitle("添加");
        else {
            mActionBar.setTitle("编辑");
            btn.setText("保存");
        }
        mActionBar.setDisplayHomeAsUpEnabled(true);//设置返回箭头显示
        mActionBar.setHomeButtonEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn = (Button)findViewById(R.id.add_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                if(type.equals("add")) {
                    Intent data = new Intent();
                    Data ret = new Data(-1,title.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString(),
                            mark.getText().toString(),mType,util.getTimeStr());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",ret);
                    data.putExtras(bundle);
                    setResult(MainActivity.CODE_ADD, data); //设置返回数据
                    finish(); //关闭当前Activity
                }else{
                    Intent data = new Intent();
                    Data ret = new Data(mData.getId(),title.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString(),
                            mark.getText().toString(),mType,util.getTimeStr());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",ret);
                    data.putExtras(bundle);
                    setResult(MainActivity.CODE_EDIT, data); //设置返回数据
                    finish(); //关闭当前Activity
                }
                break;
        }
    }
}
