package top.laobo9.pwbox.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import top.laobo9.pwbox.R;
import top.laobo9.pwbox.view.Lock.UnLockView;

/**
 * Created by wax on 2017/4/2.
 */

public class SetLockActivity extends AppCompatActivity implements UnLockView.ResponseInput {
    private Toolbar toolbar;
    private UnLockView mUnLockView;
    private TextView hint;
    private int id;
    private String pw1;
    private String pw2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlock);
        mUnLockView = (UnLockView) findViewById(R.id.unlockview);
        mUnLockView.setIsok(false);
        toolbar = (Toolbar)findViewById(R.id.setlock_toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        toolbar.setTitle("设置密码");
        mActionBar.setDisplayHomeAsUpEnabled(true);//设置返回箭头显示
        mActionBar.setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hint = (TextView)findViewById(R.id.setlock_hint);
        id = 1;
    }

    @Override
    public void onEnd(String pw) {
        if(id==1){
            pw1 = pw;
            id=2;
            mUnLockView.uninit();
            hint.setText("确认手势密码");
        }else{
            pw2 = pw;
            if(pw1.equals(pw2)){
                mUnLockView.setIsok(true);
                Properties prop = new Properties();
                prop.put("pw", pw1);
                util.util.saveConfig(this, prop);
                hint.setText("手势密码设置成功！");
                new Thread(new Runnable(){
                    public void run(){
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finish();
                }
                }).start();
            }else{
                id=1;
                mUnLockView.uninit();
                hint.setText("手势密码不一致，请重新输入!");
            }
        }
    }

    @Override
    public void inputOK() {
        //Toast.makeText(this, "密码正确", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void inputErr() {

        //Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
    }
}