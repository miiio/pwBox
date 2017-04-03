package top.laobo9.pwbox.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import top.laobo9.pwbox.R;
import top.laobo9.pwbox.view.Lock.UnLockView;

/**
 * Created by wax on 2017/4/3.
 */

public class LockActivity extends AppCompatActivity implements UnLockView.ResponseInput{
    private String mRigPw;
    private UnLockView mUnLockView;
    private TextView hint;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        mUnLockView = (UnLockView)findViewById(R.id.lockView);
        hint = (TextView)findViewById(R.id.lock_hint);
        Intent intent = getIntent();
        mRigPw = intent.getStringExtra("pw");
        mUnLockView.setmRightPsw(mRigPw);
    }

    @Override
    public void onEnd(String pw) {
        mUnLockView.uninit();
    }

    @Override
    public void inputOK() {
        hint.setText("手势密码验证成功！");
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intentt = new Intent(LockActivity.this,MainActivity.class);
                intentt.putExtra("flag","ok");
                startActivity(intentt);
                finish();
            }
        }).start();
    }

    @Override
    public void inputErr() {
        hint.setText("手势密码错误！请重试");
    }
}
