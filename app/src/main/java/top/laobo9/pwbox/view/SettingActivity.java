package top.laobo9.pwbox.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Properties;

import top.laobo9.pwbox.R;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private boolean hasPw;
    private Properties mProp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar)findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        toolbar.setTitle("设置");
        mActionBar.setDisplayHomeAsUpEnabled(true);//设置返回箭头显示
        mActionBar.setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.setting_setPw).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        mProp = util.util.loadConfig(this);
        if(mProp.size()!=0 && !mProp.getProperty("pw").equals("null")){
            unPw(false);
            hasPw=true;
        }else{
            hasPw=false;
            if(mProp.size()==0) {
                Properties prop = new Properties();
                prop.put("pw", "null");
                util.util.saveConfig(this, prop);
            }
        }
        super.onResume();
    }

    public void unPw(boolean is){
        TextView t1 = (TextView)findViewById(R.id.setting_setPw_t1);
        TextView t2 = (TextView)findViewById(R.id.setting_setPw_t2);
        if(is){
            t1.setText("设置手势密码保护");
            t2.setText("每次进入软件不需要手势密码验证");
        }else {
            t1.setText("取消手势密码保护");
            t2.setText("每次进入软件需要手势密码验证");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_setPw:
                if(!hasPw){
                    Intent intent = new Intent(this,SetLockActivity.class);
                    startActivity(intent);
                }else{
                    unPw(true);
                    hasPw=false;
                    Properties prop = new Properties();
                    prop.put("pw", "null");
                    util.util.saveConfig(this, prop);
                }
                break;
        }
    }
}
